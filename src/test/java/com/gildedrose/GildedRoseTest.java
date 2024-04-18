package com.gildedrose;

import gildedrose.GildedRose;
import gildedrose.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.discovery.predicates.IsTestClassWithTests;
import org.junit.jupiter.engine.discovery.predicates.IsTestFactoryMethod;
import org.junit.jupiter.engine.discovery.predicates.IsTestMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("Update quality then decrements it by one then sell")
    void updateQualityThenDecrementsItByOneThenSell() {
        Item[] items = new Item[] {new Item("Cookie", 1, 1)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Cookie");

        Assertions.assertEquals("Cookie", item.name);
        Assertions.assertEquals(0, item.sellIn);
        Assertions.assertEquals(0, item.quality);
    }


}
