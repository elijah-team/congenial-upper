package tripleo.util.range;

public interface RangeImpl {
   RangeImpl getNext();

   int getHighest();

   int getLowest();

   void setNext(RangeImpl var1);

   void setLowest(int var1);
}
