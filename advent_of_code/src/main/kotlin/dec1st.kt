fun main(args: Array<String>) {
    println("start dec 1st")

    partTwo();
}

private fun partOne() {
    val input = getInput();
    println("we have " + input.size + " things")

    val itemsBiggerThan1000 = input.filter { it > 1000 }
    val itemsSmallerThan1000 = input.filter { it < 1000 }
    println("we have ${itemsBiggerThan1000.size} things bigger than 1000")
    println("we have ${itemsSmallerThan1000.size} things smaller than 1000")

    val pairs = ArrayList<Pair<Int, Int>>()
    for (smallItem in itemsSmallerThan1000) {
        println("trying to find a pair for $smallItem")

        for (bigItem in itemsBiggerThan1000) {
            if (bigItem + smallItem == 2020) {
                pairs.add(Pair(smallItem, bigItem))
            }
        } // end big items loop
    } // end small item loop

    println("we got ${pairs.size} items")
    print(pairs.toString())
}

private fun partTwo() {
    val input = getInput();
    println("we have " + input.size + " things")

    val itemsBiggerThan1000 = input.filter { it > 1000 }
    val itemsSmallerThan1000 = input.filter { it < 1000 }
    println("we have ${itemsBiggerThan1000.size} things bigger than 1000")
    println("we have ${itemsSmallerThan1000.size} things smaller than 1000")

    var triple: Triple<Int, Int, Int>? = null
    var found = false
    var counterA = 0

    while (found == false && counterA < itemsSmallerThan1000.size) {
        val smallItem = itemsSmallerThan1000[counterA]
        var counterB = 0

        while(found == false && counterB < itemsSmallerThan1000.size) {
            val otherSmallItem = itemsSmallerThan1000[counterB]
            var counterC = 0

            while(found == false && counterC < itemsBiggerThan1000.size) {
                val bigItem = itemsBiggerThan1000[counterC]

                if (smallItem + otherSmallItem + bigItem == 2020) {
                    if (smallItem != otherSmallItem) {
                        found = true;
                        triple = Triple(smallItem, otherSmallItem, bigItem)
                    }
                }

                counterC ++
            }
            counterB ++
        }
        counterA ++
    }


    if (triple == null) {
        println("we didn't find an item :(")
    } else {
        print(triple.toString())
    }


}

private fun getInput() : List<Int> {
    return arrayListOf(
        1772,
        1065,
        1827,
        1671,
        1181,
        1915,
        1657,
        1632,
        1053,
        1546,
        1039,
        1388,
        1698,
        1174,
        1275,
        1250,
        1988,
        1078,
        1075,
        1958,
        1617,
        1387,
        1543,
        1965,
        1867,
        1771,
        1755,
        1331,
        1677,
        1935,
        1488,
        911,
        1001,
        1516,
        1949,
        1626,
        1083,
        1402,
        1223,
        1179,
        2001,
        1790,
        1551,
        1117,
        1990,
        1968,
        1532,
        1999,
        1175,
        1126,
        1869,
        1666,
        1753,
        513,
        1349,
        1139,
        1941,
        1823,
        1647,
        1835,
        1943,
        1459,
        1833,
        1398,
        1877,
        1625,
        1749,
        1631,
        1864,
        1826,
        1499,
        1336,
        1264,
        1091,
        1558,
        1321,
        1754,
        1729,
        1585,
        1740,
        1767,
        1774,
        1164,
        1318,
        1930,
        1236,
        1995,
        1611,
        1319,
        1361,
        1119,
        1563,
        1578,
        1047,
        1797,
        1787,
        1038,
        1921,
        1656,
        1898,
        1828,
        1727,
        1825,
        2010,
        536,
        1395,
        1865,
        1882,
        1638,
        1954,
        1565,
        1296,
        1723,
        1187,
        60,
        1130,
        1102,
        1963,
        1048,
        1493,
        1795,
        472,
        1496,
        1278,
        1444,
        1889,
        860,
        1975,
        1961,
        1070,
        1570,
        1495,
        1644,
        1881,
        1293,
        1090,
        1906,
        1385,
        1549,
        1143,
        1195,
        2004,
        1397,
        1032,
        1681,
        2000,
        1574,
        1400,
        1911,
        1868,
        1917,
        1872,
        1696,
        1086,
        1291,
        1761,
        1703,
        1202,
        1486,
        1705,
        1924,
        1186,
        1676,
        1615,
        1951,
        1556,
        1604,
        1534,
        2002,
        1334,
        1109,
        1108,
        1713,
        1422,
        1909,
        1418,
        1592,
        1887,
        1037,
        1568,
        1914,
        1780,
        1929,
        1973,
        1684,
        1581,
        1148,
        1931,
        1619,
        1082,
        1166,
        1913,
        1312,
        1330,
        1540,
        1841,
        1977,
        1769,
        1691,
        1821)
}
