package lesson3

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BinarySearchTreeTest : AbstractBinarySearchTreeTest() {

    override fun create(): CheckableSortedSet<Int> =
        BinarySearchTree()

    @Test
    @Tag("Example")
    fun initTestJava() {
        doInitTest()
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        val tree = BinarySearchTree<Int>()
        assertEquals(0, tree.size)
        assertEquals("Empty Tree", tree.toString())
        tree.add(4)
        assertEquals("[4]", tree.toString())
        tree.add(3)
        tree.add(1)
        tree.add(6)
        assertEquals("[4, 3, 1, 6]", tree.toString())
        tree.add(2)
        assertEquals(5, tree.size)
        assertEquals("[4, 3, 1, 2, 6]", tree.toString())
        tree.add(5)
        tree.add(7)
        assertEquals(7, tree.size)
        assertEquals("[4, 3, 1, 2, 6, 5, 7]", tree.toString())
    }

    @Test
    @Tag("Example")
    fun firstAndLastTestJava() {
        val tree = BinarySearchTree<Int>()
        assertEquals(null, tree.first())
        tree.add(4)
        assertEquals(4, tree.first())
        assertEquals(4, tree.last())
        tree.add(3)
        assertEquals(3, tree.first())
        tree.add(1)
        assertEquals(1, tree.first())
        assertEquals(4, tree.last())
        tree.add(6)
        assertEquals(6, tree.last())
    }

    @Test
    @Tag("5")
    fun removeTestJava() {
        val tree = BinarySearchTree<Int>()
        tree.add(4)
        tree.add(2)
        tree.add(1)
        tree.add(6)
        tree.add(3)
        assertEquals("[4, 2, 1, 3, 6]", tree.toString())
        tree.remove(6)
        assertEquals("[4, 2, 1, 3]", tree.toString())
        tree.add(6)
        tree.add(5)
        tree.add(7)
        assertEquals("[4, 2, 1, 3, 6, 5, 7]", tree.toString())
        tree.remove(6)
        assertEquals("[4, 2, 1, 3, 5, 7]", tree.toString())

        val tree2 = BinarySearchTree<Int>()
        tree2.add(4)
        tree2.add(8)
        tree2.add(5)
        tree2.add(12)
        tree2.add(6)
        tree2.add(7)
        assertEquals("[4, 8, 5, 6, 7, 12]", tree2.toString())
        tree2.remove(8)
        assertEquals("[4, 7, 5, 6, 12]", tree2.toString())
    }

    @Test
    @Tag("5")
    fun iteratorTestJava() {
        doIteratorTest()
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
    }

    @Test
    @Tag("5")
    fun subSetTestJava() {
        doSubSetTest()
    }

    @Test
    @Tag("8")
    fun subSetRelationTestJava() {
        doSubSetRelationTest()
    }

    @Test
    @Tag("7")
    fun subSetFirstAndLastTestJava() {
        doSubSetFirstAndLastTest()
    }

    @Test
    @Tag("4")
    fun headSetTestJava() {
        doHeadSetTest()
    }

    @Test
    @Tag("7")
    fun headSetRelationTestJava() {
        doHeadSetRelationTest()
    }

    @Test
    @Tag("4")
    fun tailSetTestJava() {
        doTailSetTest()
    }

    @Test
    @Tag("7")
    fun tailSetRelationTestJava() {
        doTailSetRelationTest()
    }

}