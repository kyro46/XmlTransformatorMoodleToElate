/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.main;

import java.util.ArrayList;
import java.util.List;

import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.AddonTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.AddonSubTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.Config;
import de.thorstenberger.taskmodel.complex.complextaskdef.McSubTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.TextSubTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.ClozeTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.MappingTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.TextTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.ClozeTaskBlock.ClozeConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.MappingTaskBlock.MappingConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock.McConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock.McConfig.Different;
import de.thorstenberger.taskmodel.complex.complextaskdef.MappingSubTaskDef;

public class CategoryManager {

	Category category = new Category();
	boolean hasTextTaskBlock = false;
	boolean hasMcTaskBlock = false;
	boolean hasClozeTaskBlock = false;
	boolean hasMappingTaskBlock = false;
	boolean hasAddonTaskBlock = false;

	public int defaultgrade = 0;
	
	
	String title;
	MappingTaskBlock mappingTaskBlock = new MappingTaskBlock();
	McTaskBlock mcTaskBlock = new McTaskBlock();
	TextTaskBlock textTaskBlock = new TextTaskBlock();
	ClozeTaskBlock clozeTaskBlock = new ClozeTaskBlock();
	AddonTaskBlock addonTaskBlock = new AddonTaskBlock();

//	List<TextTaskBlock> textTaskBlockList = new ArrayList<TextTaskBlock>();
//	List<McTaskBlock> mcTaskBlockList = new ArrayList<McTaskBlock>();
//	List<ClozeTaskBlock> clozeTaskBlockList = new ArrayList<ClozeTaskBlock>();
//	List<MappingTaskBlock> mappingTaskBlockList = new ArrayList<MappingTaskBlock>();
//	List<AddonTaskBlock> addonTaskBlockList = new ArrayList<AddonTaskBlock>();

	public CategoryManager(Category category) {
		this.title = category.getTitle().toString();
		this.category = category;
	}

	public boolean isHasTextTaskBlock() {
		return hasTextTaskBlock;
	}

	public void setHasTextTaskBlock(boolean hasTextTaskBlock) {
		this.hasTextTaskBlock = hasTextTaskBlock;
	}

	public boolean isHasMcTaskBlock() {
		return hasMcTaskBlock;
	}

	public void setHasMcTaskBlock(boolean hasMcTaskBlock) {
		this.hasMcTaskBlock = hasMcTaskBlock;
	}

	public boolean isHasClozeTaskBlock() {
		return hasClozeTaskBlock;
	}

	public void setHasClozeTaskBlock(boolean hasClozeTaskBlock) {
		this.hasClozeTaskBlock = hasClozeTaskBlock;
	}

	public boolean isHasMappingTaskBlock() {
		return hasMappingTaskBlock;
	}

	public void setHasMappingTaskBlock(boolean hasMappingTaskBlock) {
		this.hasMappingTaskBlock = hasMappingTaskBlock;
	}

	public boolean isHasAddonTaskBlock() {
		return hasAddonTaskBlock;
	}

	public void setHasAddonTaskBlock(boolean hasAddonTaskBlock) {
		this.hasAddonTaskBlock = hasAddonTaskBlock;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMappingTaskBlock(MappingSubTaskDef mappingSubTaskDef,String defaultgrade) {
		
		this.mappingTaskBlock = new MappingTaskBlock();
		Config generalTaskBlockConfig = new Config();
		generalTaskBlockConfig.setNoOfSelectedTasks(1);
		generalTaskBlockConfig.setPointsPerTask(Float.parseFloat(defaultgrade));
		generalTaskBlockConfig.setPreserveOrder(false);
		this.mappingTaskBlock.setConfig(generalTaskBlockConfig);
		MappingConfig mappingConfig = new MappingConfig();
		//TODO NegativePoints aus Moodle beziehen
		mappingConfig.setNegativePoints(1);
		this.mappingTaskBlock.setMappingConfig(mappingConfig);
		
		this.mappingTaskBlock.getMappingSubTaskDefOrChoice().add(mappingSubTaskDef);
		
//		this.mappingTaskBlockList.add(mappingTaskBlock);
		
		category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(mappingTaskBlock);
	}

	public void setMcTaskBlock(McSubTaskDef mcSubTaskDef,String defaultgrade) {
		mcTaskBlock = new McTaskBlock();
		Config generalTaskBlockConfig = new Config();
		generalTaskBlockConfig.setNoOfSelectedTasks(1);
		generalTaskBlockConfig.setPointsPerTask(Float.parseFloat(defaultgrade));
		generalTaskBlockConfig.setPreserveOrder(false);
		
		mcTaskBlock.setConfig(generalTaskBlockConfig);
		McConfig mcConfig = new McConfig();
		Different different = new Different();
		//TODO NegativePoints aus Moodle beziehen
		different.setCorrectAnswerNegativePoints(1);
		different.setIncorrectAnswerNegativePoints(0);
		mcConfig.setDifferent(different);
		mcTaskBlock.setMcConfig(mcConfig);
		
		mcTaskBlock.getMcSubTaskDefOrChoice().add(mcSubTaskDef);
		
//		this.mcTaskBlockList.add(mcTaskBlock);
		
		category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(mcTaskBlock);
	}

	public void setTextTaskBlock(TextSubTaskDef textSubTaskDef,String defaultgrade) {
		
		textTaskBlock = new TextTaskBlock();
		Config generalTaskBlockConfig = new Config();
		generalTaskBlockConfig.setNoOfSelectedTasks(1);
		generalTaskBlockConfig.setPointsPerTask(Float.parseFloat(defaultgrade));
		generalTaskBlockConfig.setPreserveOrder(false);
		
		textTaskBlock.setConfig(generalTaskBlockConfig);

		textTaskBlock.getTextSubTaskDefOrChoice().add(textSubTaskDef);
		
//		this.textTaskBlockList.add(textTaskBlock);
		
		category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(textTaskBlock);
	}

	public void setClozeTaskBlock(ClozeSubTaskDef clozeSubTaskDef,float defaultgrade) {
		clozeTaskBlock = new ClozeTaskBlock();
		Config generalTaskBlockConfig = new Config();
		generalTaskBlockConfig.setNoOfSelectedTasks(1);
		generalTaskBlockConfig.setPointsPerTask(defaultgrade);
		generalTaskBlockConfig.setPreserveOrder(false);
		
		// Vorbereitung ClozeTaskBlock
		clozeTaskBlock.setConfig(generalTaskBlockConfig);
		ClozeConfig clozeConfig = new ClozeConfig();
		clozeConfig.setIgnoreCase(true);
		//TODO NegativePoints aus Moodle beziehen
		clozeConfig.setNegativePoints(1);
		clozeTaskBlock.setClozeConfig(clozeConfig);

		clozeTaskBlock.getClozeSubTaskDefOrChoice().add(clozeSubTaskDef);
		
//		this.clozeTaskBlockList.add(clozeTaskBlock);
		
		category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(clozeTaskBlock);
	}
	
	public void setAddonTaskBlock(AddonSubTaskDef addonSubTaskDef,String defaultgrade){
		addonTaskBlock = new AddonTaskBlock();
		Config generalTaskBlockConfig = new Config();
		generalTaskBlockConfig.setNoOfSelectedTasks(1);
		generalTaskBlockConfig.setPointsPerTask(Float.parseFloat(defaultgrade));
		generalTaskBlockConfig.setPreserveOrder(false);
		
		addonTaskBlock.setConfig(generalTaskBlockConfig);

		addonTaskBlock.getAddonSubTaskDefOrChoice().add(addonSubTaskDef);
		
//		this.addonTaskBlockList.add(addonTaskBlock);
		
		category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(addonTaskBlock);
		
	}

	//TODO Beibehaltung der Aufgabenreihenfolge pro Category  wird steht aus
	public void generateCategory() {
//		if (hasClozeTaskBlock) {
//			for (int i = 0; i < clozeTaskBlockList.toArray().length; i ++){
//				category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(
//						clozeTaskBlockList.get(i));
//			}
//
//		}
//		if (hasTextTaskBlock) {
//						
//			for (int i = 0; i < textTaskBlockList.toArray().length; i ++){
//				category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(
//						textTaskBlockList.get(i));
//			}
//		}
//		if (hasMappingTaskBlock) {
//			for (int i = 0; i < mappingTaskBlockList.toArray().length; i ++){
//				category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(
//						mappingTaskBlockList.get(i));
//			}
//		}
//		if (hasMcTaskBlock) {
//			for (int i = 0; i < mcTaskBlockList.toArray().length; i ++){
//				category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(
//						mcTaskBlockList.get(i));
//			}
//		}
//		if (hasAddonTaskBlock) {
//			for (int i = 0; i < addonTaskBlockList.toArray().length; i ++){
//				category.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock().add(
//						addonTaskBlockList.get(i));
//			}
//		}
		
		
		
		
		
	}

	public Category getCategory(){
		return category;
	}
	
	
	
}