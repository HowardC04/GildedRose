package gildedrose;

public class GildedRose {
    public Item[] items;
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * Gets the first item that matches it's name
     * @param itemName Name of item to get
     * @return The first item in items with the same name
     */
    public Item getItem(String itemName){
        for(Item item : items){
            if(itemName.equals(item.name)){
                return item;
            }
        }
        return new Item("Failed", 0,0);
    }

    public void updateQuality() {
        for (Item item : items) {
            if(specialItem(item)){
                increaseSpecialItemQuality(item);
            }else{
                decreaseQuality(item);
            }

            decreaseSellIn(item);

        }
    }

    public boolean isAgedBrie(Item item){
        return item.name.equals("Aged Brie");
    }

    public boolean isSulfuras(Item item){
        return item.name.equals(("Sulfuras, Hand of Ragnaros"));
    }

    public boolean isBackstagePass(Item item){
        return item.name.equals(("Backstage passes to a TAFKAL80ETC concert"));
    }

    public void decreaseQuality(Item item){
        if(item.quality > 0 && !isSulfuras(item)){
            item.quality--;
        }
        if(outOfDate(item)){
            item.quality--;
        }
    }

    public void decreaseSellIn(Item item){
        if(!isSulfuras(item)){
            item.sellIn--;
        }
        if(isBackstagePass(item) && item.sellIn <= 0){
            item.quality = 0;
        }
    }

    public boolean specialItem(Item item){
        return item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    public boolean outOfDate(Item item){
        return item.sellIn <= 0;
    }

    public void increaseQuality(Item item){
        if(item.quality < MAX_QUALITY){
            item.quality++;
        }
    }

    public void increaseSpecialItemQuality(Item item){
        increaseQuality(item);
        if(isAgedBrie(item) && outOfDate(item)){
            increaseQuality(item);
        }

        if(isBackstagePass(item)){
            increaseQuality(item);
            if(item.sellIn < 6){
                increaseQuality(item);
            }
        }
    }
}
