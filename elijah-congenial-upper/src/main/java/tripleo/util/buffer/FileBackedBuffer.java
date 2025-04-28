package tripleo.util.buffer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileBackedBuffer implements TextBuffer {
   private Buffer _parent;
   private Buffer _next;
   private Buffer _previous;
   TextBuffer backing = new DefaultBuffer("");
   private String fn;

   public void finalize() {
      this.dispose();
   }

   public void dispose() {
      try {
         FileOutputStream fileOutputStream = new FileOutputStream(this.fn);
         fileOutputStream.write(this.backing.toString().getBytes());
         fileOutputStream.close();
      } catch (FileNotFoundException var2) {
         var2.printStackTrace();
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public FileBackedBuffer(String fn) {
      this.fn = fn;
   }

   public void append(String string) {
      this.backing.append(string);
   }

   public void append_s(String string) {
      this.backing.append_s(string);
   }

   public void append_cb(String string) {
      this.backing.append_cb(string);
   }

   public void decr_i() {
      this.backing.decr_i();
   }

   public void incr_i() {
      this.backing.incr_i();
   }

   public void append_nl_i(String string) {
      this.backing.append_nl_i(string);
   }

   public void append_nl(String string) {
      this.backing.append_nl(string);
   }

   public void append_ln(String string) {
      this.backing.append_ln(string);
   }

   public String getText() {
      return this.backing.getText();
   }

   public Buffer next() {
      return this._next;
   }

   public Buffer previous() {
      return this._previous;
   }

   public Buffer parent() {
      return this._parent;
   }
}
