/* file: ModelC.java
   author: Bob Muller

   CSCI 1102 Computer Science 2

   This is an implementation the Model API, a part of an
   implementation of C. Shannon's n-gram algorithm for
   modeling English text.
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ModelC implements Model {

  private HashMap<String, List<Character>> map;
  private int degree;

  public ModelC(int degree, String inputText) {

    this.degree = degree;
    map = new HashMap<String, List<Character>>();
    List<Character> chars = new ArrayList<Character>();
    String subject = "";

    for(int i = 0; i < inputText.length(); i++) {
      Character c = new Character(inputText.charAt(i));

      if(map.containsKey(subject)) {
        chars = map.get(subject);
        chars.add(c);
      }
      else {
        chars = new ArrayList<Character>();
        chars.add(c);
        map.put(subject, chars);
      }
      subject = Main.extendString(subject, c, this.degree);
    }
    // Add the sentinal character to the final string.
    //
    chars = new ArrayList<Character>();
    chars.add(Main.SENTINAL);
    map.put(subject, chars);
  }

  public Character sample(String s) {
    List<Character> chars = this.map.get(s);
    int n = chars.size();
    return chars.get(randomInt(n));
  }

  public String toString() { return map.toString(); }

  private static int randomInt(int n) {
    return (int) (Math.random() * n);
  }
}
