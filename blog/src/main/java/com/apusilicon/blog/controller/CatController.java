package com.apusilicon.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.apusilicon.blog.classes.imaginery.Category;
import com.apusilicon.blog.data.CategoryDao;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "category")
public class CatController {

    @Autowired
    private CategoryDao cats;

    /**
     * View all categories saved to database
     * @return a list of all categories for blog posts
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Category> viewCats() {
        return cats.findAll();
    }

    /**
     * Adds new categories to the database
     * @param category refers to the incoming category that should be saved
     * @return confirmation/error message as string
     */
    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCats(@RequestParam String category) {
        try {
            //checks to see if the category already exists
            for (Category cat : cats.findAll()) {
                if(cat.getCategory().equals(category)){
                    return category + " already exists in database.";
                }
            }
            //if the category doesnt exist then add to database
            Category theCat = new Category();
            theCat.setCategory(category);
            cats.save(theCat);
            return category + " Saved to database.";
        } catch (Exception e) {
            //if the category param is missing return error message
            return "error saving to database, bad parameter";
        }
    }

    /**
     * Removes a category from the database
     * @param category that points to the category that should be deleted
     * @return confirmation/error message as string
     */
    @CrossOrigin
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeCats(
                            @RequestParam String category){
        //check to see if category exists
        try{
            for (Category cat : cats.findAll()) {
                if(cat.getCategory().equals(category)){
                    cats.delete(cat);
                    break;
                }
            }
            return category + " removed from database.";
        }catch(Exception e){
            //if the category param is missing return error message
            return e.toString();
        }
    }


    /**
     * return the list of fields to me used in the db entry form
     * @return list of text and select fields from database
     */
    @CrossOrigin
    @RequestMapping(value = "/databaseform", method = RequestMethod.GET)
    public HashMap<Integer, Object> dataBaseForm(){
        HashMap<Integer, Object> map = new HashMap<>();
        
        int autoCounter = 1;
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "Brand");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Brand");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "SKU");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Family");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Weight in Lbs");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Price in $ From Manufacturers Website");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "Links");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "link");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "APU");
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select an APU","A","B","C"));
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "Display");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Display size in inches");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Display X Pixels");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Display Y Pixels");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Display Brand ex. AO optronics, Samsung, LG");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Display SKU");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Refresh rate in Hz");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Variable refresh range max Hz");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Variable refresh range max Hz");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "RAM");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Ram Brand");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Ram SKU");
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select RAM Channels","Single","Dual"));
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select RAM Type","DDR3","DDR4","LPDDR4","HBM"));
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "RAM Capacity in GB");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "Storage");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Storage Brand");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Storage SKU");
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select Storage Type",
                    "7MM", "9.5MM", "MSATA", "M22280", "M22260", "M22240"));
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select Storage Format",
                    "SSD", "SSHD", "HDD"));
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select Storage Connector",
                    "M2NVME", "M2SATA", "MSATA", "SATA"));
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "Battery");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Battery Brand");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Battery SKU");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Storage Voltage");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Storage Capacity");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Battery Cell Count");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Battery Chemistry");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("divider", "Accessories");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "SD card");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "DVD Drive");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Finger Print Scanner");
        }});
        
        map.put(autoCounter++, new HashMap<String, String>(){{
            put("text", "Windows Hello Camera");
        }});
        
        
        return map;
    }
}