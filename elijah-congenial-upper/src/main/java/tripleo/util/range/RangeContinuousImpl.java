package tripleo.util.range;

public class RangeContinuousImpl implements RangeImpl {
   int start;
   int finish;
   RangeImpl next;

   public void setLowest(int start) {
      this.start = start;
   }

   public void setFinish(int finish) {
      this.finish = finish;
   }

   public RangeImpl getNext() {
      return this.next;
   }

   public int getHighest() {
      return this.finish;
   }

   public String toString() {
      return "RangeContinuousImpl{start=" + this.start + ", finish=" + this.finish + ", next=" + this.next + '}';
   }

   public int getLowest() {
      return this.start;
   }

   public void setNext(RangeImpl next) {
      this.next = next;
   }

   public RangeContinuousImpl(int start, int finish) {
      this.start = start;
      this.finish = finish;
   }
}
