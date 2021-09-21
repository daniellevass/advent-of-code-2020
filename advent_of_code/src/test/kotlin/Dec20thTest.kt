import org.junit.Test
import kotlin.test.*

class Dec20thTest {

   @Test
   fun dec20_processTile() {
       val input = arrayListOf(
               "Tile 3079:",
               "#.#.#####.",
               ".#..######",
               "..#.......",
               "######....",
               "####.#..#.",
               ".#...#.##.",
               "#.#####.##",
               "..#.###...",
               "..#.......",
               "..#.###...")

       val output = dec20_processTile(input)
       assertNotNull(output)
        assertEquals(3079, output.key)
       assertEquals("#.#.#####.", output.edges[0]) // top
       assertEquals(".#....#...", output.edges[1]) // right
       assertEquals("..#.###...", output.edges[2]) // bottom
       assertEquals("#..##.#...", output.edges[3]) // left

   }

    @Test
    fun dec20_readFile() {
        val output = dec20_readFile("src/main/resources/dec20_sample.txt")

        assertEquals(9, output.size)
    }

    @Test
    fun dec20_determineIfMatch_true() {

        val inputTile1427 = arrayListOf("Tile 1427:",
                "###.##.#.." ,
                ".#..#.##.." ,
                ".#.##.#..#" ,
                "#.#.#.##.#" ,
                "....#...##" ,
                "...##..##." ,
                "...#.#####" ,
                ".#.####.#." ,
                "..#..###.#" ,
                "..##.#..#.")
        val inputTile1489 = arrayListOf("Tile 1489:" ,
                "##.#.#...." ,
                "..##...#.." ,
                ".##..##..." ,
                "..#...#..." ,
                "#####...#." ,
                "#..#.#.#.#" ,
                "...#.#.#.." ,
                "##.#...##." ,
                "..##.##.##" ,
                "###.##.#..")
        val inputTile3079 = arrayListOf("Tile 3079:" ,
                "#.#.#####." ,
                ".#..######" ,
                "..#......." ,
                "######...." ,
                "####.#..#." ,
                ".#...#.##." ,
                "#.#####.##" ,
                "..#.###..." ,
                "..#......." ,
                "..#.###...")

        val tile1427 = dec20_processTile(inputTile1427)
        val tile1489 = dec20_processTile(inputTile1489)
        val tile3079 = dec20_processTile(inputTile3079)

        assertTrue(dec20_determineIfMatch(tile1427, tile1489))
        assertFalse(dec20_determineIfMatch(tile1427, tile3079))
        assertFalse(dec20_determineIfMatch(tile1489, tile3079))

    }

    @Test
    fun dec20_orientShape90() {
        val shape = Dec20Shape(key = 1, edges = arrayListOf("123", "369", "789", "147"), content = arrayListOf("123", "456", "789"))

        val rotatedShape = dec20_orientShape90(shape)

        assertEquals(arrayListOf("741", "852", "963"), rotatedShape.content)
        assertEquals(arrayListOf("741", "123", "963", "789"), rotatedShape.edges)

    }
}

