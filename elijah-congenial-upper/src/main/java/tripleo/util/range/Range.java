package tripleo.util.range;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Range implements Iterable {
   LinkedList list = new LinkedList();

   public Range() {
   }

   public Range(int start, int finish) {
      RangeContinuousImpl impl = new RangeContinuousImpl(start, finish);
      this.list.add(impl);
   }

   public void add(int single) {
      this._add(single);
      this.coalesce();
   }

   public void add(int start, int finish) {
      this._add(start, finish);
      this.coalesce();
   }

   public void remove(int single) {
      this._remove(single);
      this.coalesce();
   }

   public void remove(int start, int finish) {
      this._remove(start, finish);
      this.coalesce();
   }

   public void coalesce() {
      if (this.list.size() >= 2) {
         int removals = 0;

         while(true) {
            ListIterator iter = this.list.listIterator();

            for(RangeImpl prev = (RangeImpl)iter.next(); iter.hasNext(); prev = (RangeImpl)this.list.get(iter.previousIndex())) {
               RangeImpl next = (RangeImpl)iter.next();
               if (next.getLowest() < prev.getHighest()) {
                  if (prev.getHighest() <= next.getHighest()) {
                     ((RangeContinuousImpl)prev).setFinish(next.getHighest());
                     iter.remove();
                     ++removals;
                  } else {
                     iter.remove();
                     ++removals;
                  }
               } else if (next.getLowest() == prev.getHighest() + 1 && prev.getHighest() <= next.getHighest()) {
                  ((RangeContinuousImpl)prev).setFinish(next.getHighest());
                  iter.remove();
                  ++removals;
               }
            }

            if (removals == 0) {
               return;
            }

            removals = 0;
         }
      }
   }

   public void _add(int single) {
      boolean added = false;
      RangeSingleImpl impl = new RangeSingleImpl(single);

      for(int i = 0; i < this.list.size(); ++i) {
         RangeImpl iter = (RangeImpl)this.list.get(i);
         if (iter instanceof RangeSingleImpl) {
            if (single >= iter.getLowest() && single <= iter.getHighest()) {
               added = true;
            } else if (single < iter.getLowest() && i == 0) {
               if (single - 1 == iter.getLowest()) {
                  iter.setLowest(single - 1);
               } else {
                  RangeImpl rsi = new RangeSingleImpl(single);
                  this.list.add(0, rsi);
                  added = true;
               }
            }
         } else if (!(iter instanceof RangeContinuousImpl)) {
            throw new IllegalStateException("cant be here");
         }
      }

      if (!added) {
         this.list.add(impl);
      }

   }

   public void _add(int start, int finish) {
      boolean added = false;
      RangeImpl cand = null;

      for(int i = 0; i < this.list.size(); ++i) {
         RangeImpl iter = (RangeImpl)this.list.get(i);
         if (iter instanceof RangeSingleImpl) {
            if (start >= iter.getLowest() && start <= iter.getHighest()) {
               added = true;
            } else if (start < iter.getLowest()) {
               if (i == 0) {
                  if (start - 1 == iter.getLowest()) {
                     iter.setLowest(start - 1);
                  } else {
                     RangeImpl rsi = new RangeContinuousImpl(start, finish);
                     this.list.add(0, rsi);
                     added = true;
                  }
               }
            } else {
               added = false;
            }
         } else {
            if (!(iter instanceof RangeContinuousImpl)) {
               throw new IllegalStateException("cant be here");
            }

            if (start < iter.getLowest() || finish > iter.getHighest()) {
               if (start < iter.getLowest()) {
                  if (i == 0) {
                     if (start - 1 == iter.getLowest()) {
                        iter.setLowest(start - 1);
                     } else {
                        RangeImpl rsi = new RangeSingleImpl(start);
                        this.list.add(0, rsi);
                     }
                  }
               } else if (start > iter.getHighest()) {
                  cand = iter;
               }
            }
         }
      }

      if (!added) {
         RangeContinuousImpl impl;
         if (cand == null) {
            impl = new RangeContinuousImpl(start, finish);
            this.list.add(impl);
         } else {
            impl = new RangeContinuousImpl(start, finish);
            this.list.add(this.list.indexOf(cand) + 1, impl);
         }
      }

   }

   public static RangeImpl mk_nod(int low, int high) {
      return (RangeImpl)(low == high ? new RangeSingleImpl(low) : new RangeContinuousImpl(low, high));
   }

   public void _remove(int single) {
      for(int i = 0; i < this.list.size(); ++i) {
         RangeImpl iter = (RangeImpl)this.list.get(i);
         if (iter instanceof RangeContinuousImpl) {
            RangeContinuousImpl rci = (RangeContinuousImpl)iter;
            if (single >= rci.getLowest() && single <= rci.getHighest()) {
               if (single == rci.getLowest()) {
                  rci.setLowest(single + 1);
               } else {
                  if (single != rci.getHighest()) {
                     RangeImpl rci1 = mk_nod(rci.getLowest(), single - 1);
                     RangeImpl rci2 = mk_nod(single + 1, rci.getHighest());
                     this.list.remove(i);
                     this.list.add(i, rci1);
                     this.list.add(i + 1, rci2);
                     break;
                  }

                  rci.setFinish(single - 1);
               }
            }
         } else {
            if (!(iter instanceof RangeSingleImpl)) {
               throw new IllegalStateException("cant be here");
            }

            RangeSingleImpl rsi = (RangeSingleImpl)iter;
            if (single == rsi.getLowest()) {
               this.list.remove(i);
            }
         }
      }

   }

   public void _remove(int start, int finish) {
      for(int i = 0; i < this.list.size(); ++i) {
         RangeImpl iter = (RangeImpl)this.list.get(i);
         if (iter instanceof RangeContinuousImpl) {
            RangeContinuousImpl rci = (RangeContinuousImpl)iter;
            RangeImpl rci1;
            RangeImpl rci2;
            if (start < rci.getLowest() || start > rci.getHighest()) {
               rci1 = mk_nod(rci.getLowest(), Math.min(start - 1, rci.getHighest()));
               rci2 = mk_nod(finish + 1, rci.getHighest());
               if (rci2.getLowest() > rci2.getHighest()) {
                  rci2 = mk_nod(finish + 1, ((RangeImpl)this.list.get(i + 1)).getHighest());
                  this.list.remove(i);
                  this.list.remove(i);
                  this.list.add(i, rci1);
                  this.list.add(i + 1, rci2);
               } else {
                  this.list.remove(i);
                  this.list.add(i, rci1);
                  this.list.add(i + 1, rci2);
               }
               break;
            }

            if (start == rci.getLowest()) {
               rci.setLowest(start + 1);
            } else {
               if (start != rci.getHighest()) {
                  rci1 = mk_nod(rci.getLowest(), start - 1);
                  rci2 = mk_nod(finish + 1, rci.getHighest());
                  if (rci2.getLowest() > rci2.getHighest()) {
                     rci2 = mk_nod(finish + 1, ((RangeImpl)this.list.get(i + 1)).getHighest());
                     this.list.remove(i);
                  }

                  this.list.remove(i);
                  this.list.add(i, rci1);
                  this.list.add(i + 1, rci2);
                  break;
               }

               rci.setFinish(start - 1);
            }
         } else {
            if (!(iter instanceof RangeSingleImpl)) {
               throw new IllegalStateException("cant be here");
            }

            RangeSingleImpl rsi = (RangeSingleImpl)iter;
            if (start == rsi.getLowest()) {
               this.list.remove(i);
            }
         }
      }

   }

   public String lispRepr() {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < this.list.size(); ++i) {
         RangeImpl iter = (RangeImpl)this.list.get(i);
         if (iter instanceof RangeContinuousImpl) {
            sb.append(String.format("(c %d %d)", iter.getLowest(), iter.getHighest()));
         } else {
            if (!(iter instanceof RangeSingleImpl)) {
               throw new IllegalStateException("cant be here");
            }

            sb.append(String.format("(s %d)", iter.getLowest()));
         }
      }

      sb.append("(end)");
      return sb.toString();
   }

   public Iterator iterator() {
      return new Iterator() {
         int i = 0;
         int ii;

         public boolean hasNext() {
            if (this.i + 1 >= Range.this.list.size() && this.i + 1 <= Range.this.list.size()) {
               RangeImpl range = (RangeImpl)Range.this.list.get(this.i);
               if (range instanceof RangeContinuousImpl) {
                  if (this.ii == range.getHighest()) {
                     int R = range.getHighest();
                     ++this.i;
                     this.ii = 0;
                     return this.hasNext();
                  } else {
                     return true;
                  }
               } else if (range instanceof RangeSingleImpl) {
                  ++this.i;
                  this.ii = 0;
                  return true;
               } else {
                  throw new IllegalStateException("can't be here");
               }
            } else {
               return false;
            }
         }

         public Integer next() {
            if (Range.this.list.size() >= this.i) {
               RangeImpl range = (RangeImpl)Range.this.list.get(this.i);
               int R;
               if (range instanceof RangeContinuousImpl) {
                  if (this.ii == range.getHighest()) {
                     R = range.getHighest();
                     ++this.i;
                     this.ii = 0;
                     return R;
                  } else {
                     return range.getLowest() + this.ii++;
                  }
               } else if (range instanceof RangeSingleImpl) {
                  R = range.getLowest();
                  ++this.i;
                  this.ii = 0;
                  return R;
               } else {
                  throw new IllegalStateException("can't be here");
               }
            } else {
               throw new IndexOutOfBoundsException("" + Range.this.list.size() + " " + this.i);
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("doesn't support remove right now.");
         }
      };
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      Iterator var2 = this.iterator();

      while(var2.hasNext()) {
         Integer integer = (Integer)var2.next();
         sb.append(integer);
         sb.append(", ");
      }

      return sb.toString();
   }

   public boolean has(int v) {
      ListIterator x = this.list.listIterator();

      RangeImpl r;
      do {
         if (!x.hasNext()) {
            return false;
         }

         r = (RangeImpl)x.next();
      } while(v < r.getLowest() || v > r.getHighest());

      return true;
   }
}
