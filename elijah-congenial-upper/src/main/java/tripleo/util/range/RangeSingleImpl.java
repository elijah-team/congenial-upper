package tripleo.util.range;

public class RangeSingleImpl implements RangeImpl {
   int single;
   RangeImpl next;

   public int getHighest() {
      return this.single;
   }

   public int getLowest() {
      return this.single;
   }

   public void setLowest(int single) {
      this.single = single;
   }

   public RangeImpl getNext() {
      return this.next;
   }

   public void setNext(RangeImpl next) {
      this.next = next;
   }

   public String toString() {
      return "RangeSingleImpl{single=" + this.single + ", next=" + this.next + '}';
   }

   public RangeSingleImpl(int single) {
      this.single = single;
   }
}
