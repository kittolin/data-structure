package tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    private Trie trie;

    @Before
    public void setUp() throws Exception {
        trie = new Trie();
        trie.add("code");
        trie.add("java");
        trie.add("panda");
    }

    @Test
    public void testGetSize() {
        assertEquals(3, trie.getSize());
    }

    @Test
    public void testAdd() {
        trie.add("");
        assertEquals(3, trie.getSize());

        trie.add("code");
        assertEquals(3, trie.getSize());

        trie.add("pan");
        assertEquals(4, trie.getSize());

        trie.add("china");
        assertEquals(5, trie.getSize());
    }

    @Test
    public void testContains() {
        assertFalse(trie.contains(null));
        assertFalse(trie.contains(""));
        assertFalse(trie.contains("python"));
        assertTrue(trie.contains("panda"));

        assertFalse(trie.contains("pan"));
        trie.add("pan");
        assertTrue(trie.contains("pan"));
    }

    @Test
    public void testStartsWith() {
        assertFalse(trie.startsWith(null));
        assertTrue(trie.startsWith(""));
        assertTrue(trie.startsWith("pan"));
        assertTrue(trie.startsWith("panda"));
        assertFalse(trie.startsWith("python"));
    }

}
