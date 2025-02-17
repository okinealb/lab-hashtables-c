import java.io.PrintWriter;

/**
 * A simple set of experiments using our new hash tables.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HashTableExperiments {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * A word list stolen from some tests that SamR wrote in the distant past.
   */
  static String[] words = {"aardvark", "anteater", "antelope", "bear", "bison",
      "buffalo", "chinchilla", "cat", "dingo", "elephant", "eel",
      "flying squirrel", "fox", "goat", "gnu", "goose", "hippo", "horse",
      "iguana", "jackalope", "kestrel", "llama", "moose", "mongoose", "nilgai",
      "orangutan", "opossum", "red fox", "snake", "tarantula", "tiger",
      "vicuna", "vulture", "wombat", "yak", "zebra", "zorilla"};

  // +-------------+-----------------------------------------------------
  // | Experiments |
  // +-------------+

  /**
   * A short experiment with getting a value from the hash table.
   */
  public static void checkGet(PrintWriter pen, HashTable<String,String> htab,
      String key) {
    pen.print("Getting " + key + " ... ");
    pen.flush();
    try {
      pen.println(htab.get(key));
    } catch (Exception e) {
      pen.println("Failed because " + e);
    } // try/catch
  } // checkGet(PrintWriter, HashTable<String,String>, String)

  /**
   * Explore what happens when we use set with a repeated key.
   */
  public static void repeatedSetExpt(PrintWriter pen,
      HashTable<String,String> htab) {
    pen.println("Repeated Set Experiment");
    htab.reportBasicCalls(true);
    htab.set("alpha", "alpha");
    htab.dump(pen);
    htab.set("beta", "beta");
    htab.dump(pen);
    htab.set("bravo", "bravo");
    htab.dump(pen);
    htab.set("beta", "bravo");
    htab.dump(pen);
    htab.reportBasicCalls(false);
    checkGet(pen, htab, "beta");
    pen.println();
  } // repeatedSetExpt(PrintWriter, HashTable)

  /**
   * Explore what happens when we use two keys that map to the same location.
   */
  public static void matchingKeysExpt(PrintWriter pen,
      HashTable<String,String> htab) {
    pen.println("Setting anteater");
    htab.set("anteater", "anteater");
    checkGet(pen, htab, "anteater");
    checkGet(pen, htab, "buffalo");
    htab.dump(pen);
    pen.println();
  } // matchingKeysExpt(PrintWriter, HashTable)

  /**
   * Explore what happens when we use two keys that map to the same location.
   */
  public static void matchingSetExpt(PrintWriter pen,
      HashTable<String,String> htab) {
    pen.println("Setting anteater");
    htab.set("anteater", "anteater");
    htab.set("buffalo", "buffalo");
    checkGet(pen, htab, "anteater");
    checkGet(pen, htab, "buffalo");
    htab.dump(pen);
    pen.println();
  } // matchingSetExpt(PrintWriter, HashTable)

  /**
   * Explore what happens when we use set with a wide variety of key/value
   * pairs.
   */
  public static void multipleSetExpt(PrintWriter pen,
      HashTable<String,String> htab) {
    int numwords = words.length;
    for (int i = 0; i < numwords; i++) {
      // htab.dump(pen);
      htab.set(words[i], words[i]);
      for (int j = 0; j <= i; j++) {
        try {
          String str = htab.get(words[j]);
          if (!str.equals(words[j])) {
            pen.println("After setting " + words[i] + ", " + words[j]
                + " no longer yields itself.");
            htab.dump(pen);
            return;
          } // if we didn't get the expected value.
        } catch (Exception e) {
          pen.println("After setting " + words[i] + ", " + words[j]
              + " is no longer in the table.");
          htab.dump(pen);
          return;
        } // try/catch
      } // for j
    } // for i
    htab.dump(pen);
    pen.println();
  } // multipleSetExpt(PrintWriter, HashTable)

  /**
   * Explore what happens when we remove elements.
   */
  public static void removeExpt(PrintWriter pen,
      HashTable<String,String> htab) {
    htab.set("anteater", "anteater");
    htab.set("buffalo", "buffalo");
    htab.dump(pen);
    pen.println("Removing anteater ... ");
    htab.remove("anteater");
    pen.println("Getting anteater ... ");
    checkGet(pen, htab, "anteater");
  } // removeExpt(PrintWriter, HashTable)

} // class HashTableExpt
