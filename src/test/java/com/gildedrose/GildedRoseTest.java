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

    @Test
    @DisplayName("Quality decrease twice as fast after sell by date")
    void qualityDecreaseTwiceAsFastAfterSellByDate() {
        Item[] items = new Item[] {new Item("Cookie", 1, 5)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Cookie");

        Assertions.assertEquals("Cookie", item.name);
        Assertions.assertEquals(-1, item.sellIn);
        Assertions.assertEquals(2, item.quality);
    }

    @Test
    @DisplayName("Quality can never be negative")
    void qualityCanNeverBeNegative() {
        Item[] items = new Item[] {new Item("Cookie", 5, 1)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Cookie");

        Assertions.assertEquals("Cookie", item.name);
        Assertions.assertEquals(3, item.sellIn);
        Assertions.assertEquals(0, item.quality);
    }

    @Test
    @DisplayName("Aged Brie quality increase by 1 if it's not past it's sell date")
    void agedBrieQualityIncreaseBy1IfItSNotPastItSSellDate() {
        Item[] items = new Item[] {new Item("Aged Brie", 10, 0)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Aged Brie");

        Assertions.assertEquals("Aged Brie", item.name);
        Assertions.assertEquals(7, item.sellIn);
        Assertions.assertEquals(3, item.quality);
    }

    @Test
    @DisplayName("Aged Brie quality increase by 2 if it's past it's sell date")
    void agedBrieQualityIncreaseBy2IfItSPastItSSellDate() {
        Item[] items = new Item[] {new Item("Aged Brie", 0, 0)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Aged Brie");

        Assertions.assertEquals("Aged Brie", item.name);
        Assertions.assertEquals(-3, item.sellIn);
        Assertions.assertEquals(6, item.quality);
    }

    @Test
    @DisplayName("Aged Brie quality decreases correctly")
    void agedBrieQualityDecreasesCorrectly() {
        Item[] items = new Item[] {new Item("Aged Brie", 2, 0)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Aged Brie");

        Assertions.assertEquals("Aged Brie", item.name);
        Assertions.assertEquals(-1, item.sellIn);
        Assertions.assertEquals(4, item.quality);
    }

    @Test
    @DisplayName("Quality of an item never gets more then 50")
    void qualityOfAnItemNeverGetsMoreThen50() {
        Item[] items = new Item[] {new Item("Aged Brie", 2, 50)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Aged Brie");

        Assertions.assertEquals("Aged Brie", item.name);
        Assertions.assertEquals(-1, item.sellIn);
        Assertions.assertEquals(50, item.quality);
    }

    @Test
    @DisplayName("Sulfuras never have to be sold or have it's quality decrease")
    void sulfurasNeverHaveToBeSoldOrHaveItSQualityDecrease() {
        Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 2, 80)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Sulfuras, Hand of Ragnaros");

        Assertions.assertEquals("Sulfuras, Hand of Ragnaros", item.name);
        Assertions.assertEquals(2, item.sellIn);
        Assertions.assertEquals(80, item.quality);
    }

    @Test
    @DisplayName("Backstage passes quality increases by 2 when there are 10 days or less")
    void backstagePassesQualityIncreasesBy2WhenThereAre10DaysOrLess() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Backstage passes to a TAFKAL80ETC concert");

        Assertions.assertEquals("Backstage passes to a TAFKAL80ETC concert", item.name);
        Assertions.assertEquals(7, item.sellIn);
        Assertions.assertEquals(16, item.quality);
    }

    @Test
    @DisplayName("Backstage passes quality increase by 3 when there are 5 days or less")
    void backstagePassesQualityIncreaseBy3WhenThereAre5DaysOrLess() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Backstage passes to a TAFKAL80ETC concert");

        Assertions.assertEquals("Backstage passes to a TAFKAL80ETC concert", item.name);
        Assertions.assertEquals(2, item.sellIn);
        Assertions.assertEquals(19, item.quality);
    }

    @Test
    @DisplayName("Backstage passes quality increase depending on how many days left")
    void backstagePassesQualityIncreaseDependingOnHowManyDaysLeft() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Backstage passes to a TAFKAL80ETC concert");

        Assertions.assertEquals("Backstage passes to a TAFKAL80ETC concert", item.name);
        Assertions.assertEquals(3, item.sellIn);
        Assertions.assertEquals(18, item.quality);
    }

    @Test
    @DisplayName("Backstage passes quality is 0 after concert")
    void backstagePassesQualityIs0AfterConcert() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 35)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Item item = gildedRose.getItem("Backstage passes to a TAFKAL80ETC concert");

        Assertions.assertEquals("Backstage passes to a TAFKAL80ETC concert", item.name);
        Assertions.assertEquals(-1, item.sellIn);
        Assertions.assertEquals(0, item.quality);
    }



}
