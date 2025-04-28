package tripleo.util.range;

class RangeEndImpl implements RangeImpl {
   public String toString() {
      return "{end}";
   }

   public RangeImpl getNext() {
      return null;
   }

   public int getHighest() {
      return 0;
   }

   public int getLowest() {
      return 0;
   }

   public void setNext(RangeImpl impl) {
   }

   public void setLowest(int i) {
   }
}
