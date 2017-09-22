package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void test_updateEndofDay_NewItem_Quality_10_9()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("New Item", 3, 10));
		
		//Act 
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("Quality should be reduced", 9, item.getQuality());
	}
	
	@Test
	public void test_updateEndofDay_NewItem_SellIn_3_2()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("New Item", 3, 10));
		
		//Act 
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("SellIn should be reduced", 2, item.getSellIn());
	}
	
	@Test
	public void test_updateEndofDay_NewItem_Quality_10_4()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("New Item", 2, 10));
		
		//Act 
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("Quality should be reduced by one until its sellin comes then reduced by two", 4, item.getQuality());
	}
	
	@Test
	public void test_updateEndofDay_NewItem_Quality_3_0()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("New Item", 2, 3));
		
		//Act 
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("Quality should not be negative", 0, item.getQuality());
	}
	
	@Test
	public void test_updateEndofDay_AgedBrie_Quality_5_6()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Aged Brie", 3, 5));
		
		//Act 
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item agedBrie = items.get(0);
		
		//Assert
		assertEquals("Aged Brie quality should be increased", 6, agedBrie.getQuality());
	}
	
	@Test
	public void test_updateEndofDay_AgedBrie_SellIn_3_2()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Aged Brie", 3, 5));
		
		//Act 
		gRose.updateEndOfDay();
		List<Item> items = gRose.getItems();
		Item agedBrie = items.get(0);
		
		//Assert
		assertEquals("Aged Brie sellIn should be reduced", 2, agedBrie.getSellIn());
	}
	
	@Test
	public void test_updateEndofDay_Sulfuras_SellIn_3_3()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Sulfuras, Hand of Ragnaros", 3, 5));
		
		//Act 
		gRose.updateEndOfDay();
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("Sulfuras SellIn should not be reduced", 3, item.getSellIn());
	}
	
	@Test
	public void test_updateEndofDay_Sulfuras_Quality_80_80()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Sulfuras, Hand of Ragnaros", 3, 80));
		
		//Act 
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("Sulfuras quality will never change", 80, item.getQuality());
	}
		
	@Test
	public void test_updateEndofDay_Backstage_Quality_15_17()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 7, 15));
		
		//Act 
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("The selling day is less than 10 and more than 5, so quality should be increased by two", 17, item.getQuality());
	}
	
	@Test
	public void test_updateEndofDay_Backstage_Quality_10_13()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10));
		
		//Act 
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("The selling day is less than 5, so quality should be increased by three", 13, item.getQuality());
	}
	
	@Test
	public void test_updateEndofDay_Backstage_Quality_15_0()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 15));
		
		//Act 
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("Quality should drop to zero since the concert day has arrived", 0, item.getQuality());
	}
	
	@Test
	public void test_updateEndofDay_AgedBrie_Quality_45_50()
	{
		//Arrange
		GildedRose gRose = new GildedRose();
		gRose.addItem(new Item("Aged Brie", 8, 45));
		
		//Act 
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		gRose.updateEndOfDay();
		
		List<Item> items = gRose.getItems();
		Item item = items.get(0);
		
		//Assert
		assertEquals("Quality should not be more than 50", 50, item.getQuality());
	}
}
