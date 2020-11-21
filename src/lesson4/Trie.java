package lesson4;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private static class Node {
        Map<Character, Node> children = new LinkedHashMap<>();
    }

    private Node root = new Node();

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Итератор для префиксного дерева
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Сложная
     */
    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    public class TrieIterator implements Iterator<String> {
        ArrayDeque<String> words = new ArrayDeque<>();
        String currentWord;

        TrieIterator() {
            if (root != null) findWord(root, "");
        }

        void findWord(Node node, String word) {
            for (Map.Entry<Character, Node> child : node.children.entrySet()) {
                if (child.getKey() != '\0') {
                    findWord(child.getValue(), word + child.getKey());
                } else {
                    words.add(word);
                }
            }
        }

        // Трудоёмкость - O(1)
        // Ресурсоёмкость - O(1)
        @Override
        public boolean hasNext() {
            return !words.isEmpty();
        }

        //возвращает ТЕКУЩЕЕ слово и удаляет его из ОЧЕРЕДИ
        // Трудоёмкость - O(n)
        // Ресурсоёмкость - O(n)
        @Override
        public String next() {
            currentWord = words.pop();
            return currentWord;
        }

        //удаляет ТЕКУЩЕЕ слово из ДЕРЕВА
        // Трудоёмкость - O(n)
        // Ресурсоёмкость - O(n)
        @Override
        public void remove() {
            if (currentWord != null) {
                Trie.this.remove(currentWord);
                currentWord = null;
            } else {
                throw new IllegalStateException();
            }
        }
    }

}