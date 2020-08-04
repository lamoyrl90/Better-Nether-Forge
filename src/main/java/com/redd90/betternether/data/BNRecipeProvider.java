package com.redd90.betternether.data;

import java.util.function.Consumer;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

public class BNRecipeProvider extends RecipeProvider {

	public BNRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	public void stairsRecipe(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 4)
		.key('#', input)
		.patternLine("#  ")
		.patternLine("## ")
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void slabRecipe(IItemProvider output, int numberOut, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 6)
		.key('#', input)
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void fenceRecipe(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 3)
		.key('#', input)
		.key('x', Items.STICK)
		.patternLine("#x#")
		.patternLine("#x#")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer); 
	}
	
	public void gateRecipe(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.key('x', Items.STICK)
		.patternLine("x#x")
		.patternLine("x#x")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void roofRecipe(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 6)
		.key('#', input)
		.patternLine("# #")
		.patternLine("###")
		.patternLine(" # ")
		.build(consumer); 
	}
	
	public void plateRecipe(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 6)
		.key('#', input)
		.patternLine("##")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void doorRecipe(IItemProvider output, int numberOut, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, numberOut)
		.key('#', input)
		.patternLine("##")
		.patternLine("##")
		.patternLine("##")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void trapdoorRecipe(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 2)
		.key('#', input)
		.patternLine("###")
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void wallRecipe(IItemProvider output, int numberOut, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, numberOut)
		.key('#', input)
		.patternLine("###")
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void dyeRecipe(IItemProvider output, IItemProvider input, IItemProvider dye,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 8)
		.key('#', input)
		.key('D', dye)
		.patternLine("###")
		.patternLine("#D#")
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void shapeRound(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.patternLine("###")
		.patternLine("# #")
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void signRecipe(IItemProvider output, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, 3)
		.key('#', input)
		.key('x', Items.STICK)
		.patternLine("###")
		.patternLine("###")
		.patternLine(" x ")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer);
	}
	
	public void columnRecipe(IItemProvider output, int numberOut, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, numberOut)
		.key('#', input)
		.patternLine("#")
		.patternLine("#")
		.patternLine("#")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, "columncrafting_" + output.asItem().getRegistryName().getPath());
	}
	
	public void pillarRecipe(IItemProvider output, int numberOut, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, numberOut)
		.key('#', input)
		.patternLine("#")
		.patternLine("#")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, "pillarcrafting_" + output.asItem().getRegistryName().getPath()
				+ "_from_" + input.asItem().getRegistryName().getPath());
	}
	
	public void recipe2x2(IItemProvider output, int numberOut, IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, numberOut)
		.key('#', input)
		.patternLine("##")
		.patternLine("##")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, "2x2crafting_" + output.asItem().getRegistryName().getPath()); 
	}
	
	public void recipe3x3(IItemProvider output, int numberOut,IItemProvider input,Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output, numberOut)
		.key('#', input)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, "3x3crafting_" + output.asItem().getRegistryName().getPath()); 
	}
	
	public void helmetRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.patternLine("###")
		.patternLine("# #")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void chestplateRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.patternLine("# #")
		.patternLine("###")
		.patternLine("###")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void leggingsRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.patternLine("###")
		.patternLine("# #")
		.patternLine("# #")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}

	public void bootsRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.patternLine("# #")
		.patternLine("# #")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void pickaxeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.key('S', Items.STICK)
		.patternLine("###")
		.patternLine(" S ")
		.patternLine(" S ")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void axeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.key('S', Items.STICK)
		.patternLine("##")
		.patternLine("S#")
		.patternLine("S ")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void shovelRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.key('S', Items.STICK)
		.patternLine("#")
		.patternLine("S")
		.patternLine("S")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void hoeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.key('S', Items.STICK)
		.patternLine("##")
		.patternLine("S ")
		.patternLine("S ")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void swordRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder
		.shapedRecipe(output)
		.key('#', input)
		.key('S', Items.STICK)
		.patternLine("#")
		.patternLine("#")
		.patternLine("S")
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath()); 
	}
	
	public void singleItemRecipe(IItemProvider output, int numberOut, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapelessRecipeBuilder
		.shapelessRecipe(output, numberOut)
		.addIngredient(input)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, output.asItem().getRegistryName().getPath() + "_from_" + output.asItem().getRegistryName().getPath()); 
	}
	
	public void singleItemRecipe(String name, IItemProvider output, int numberOut, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		
		ShapelessRecipeBuilder
		.shapelessRecipe(output, numberOut)
		.addIngredient(input)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
		.build(consumer, name); 
	}
	
	public void singleItemRecipe(String name, IItemProvider output, int numberOut, ITag<Item> input, Consumer<IFinishedRecipe> consumer) {
		
		ShapelessRecipeBuilder
		.shapelessRecipe(output, numberOut)
		.addIngredient(input)
		.addCriterion("has_" + input.toString(), hasItem(input))
		.build(consumer, name); 
	}
	
	public void cookFood(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), 0.35F,
				200,
				IRecipeSerializer.SMELTING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "smelting/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_smelting");
	      
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), 0.35F,
				100,
				IRecipeSerializer.SMOKING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "smoking/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_smoking");
		
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), 0.35F,
				600,
				IRecipeSerializer.CAMPFIRE_COOKING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "campfire_cooking/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_campfire_cooking");
	     
	}
	
	public void smeltOre(IItemProvider output, IItemProvider input, float xp, Consumer<IFinishedRecipe> consumer) {
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), xp,
				200,
				IRecipeSerializer.SMELTING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "smelting/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_smelting");
	      
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), xp,
				100,
				IRecipeSerializer.BLASTING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "blasting/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_blasting");
	     
	}
	
	public void smeltQuick(IItemProvider output, IItemProvider input, float xp, Consumer<IFinishedRecipe> consumer) {
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), xp,
				22,
				IRecipeSerializer.SMELTING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "smelting/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_smelting");
	      
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), xp,
				11,
				IRecipeSerializer.BLASTING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "blasting/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_blasting");
	     
	}
	
	public void furnace(IItemProvider output, IItemProvider input, float xp, Consumer<IFinishedRecipe> consumer) {
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(input.asItem()),
				output.asItem(), xp,
				200,
				IRecipeSerializer.SMELTING)
		.addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input.asItem()))
		.build(consumer, "smelting/" + output.asItem().getRegistryName().getPath() + "_from_" + input.asItem().getRegistryName().getPath() + "_from_smelting");
	}
	
}
