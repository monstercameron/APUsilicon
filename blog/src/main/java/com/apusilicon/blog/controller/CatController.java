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
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "Brand");
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Brand");
            put("rules", Arrays.asList("notnull", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "SKU");
            put("rules", Arrays.asList("notnull", "20char", "alphanum"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Family");
            put("rules", Arrays.asList("notnull", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Weight in Lbs");
            put("rules", Arrays.asList("nullable", "5char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Price in $ From Manufacturers Website");
            put("rules", Arrays.asList("notnull", "8char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "Links");
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "link");
            put("rules", Arrays.asList("nullable", "anychar", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "APU");
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select an APU","A","B","C"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "Display");
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Display size in inches");
            put("rules", Arrays.asList("notnull", "4char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Display X Pixels");
            put("rules", Arrays.asList("notnull", "4char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Display Y Pixels");
            put("rules", Arrays.asList("notnull", "4char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Display Brand ex. AO optronics, Samsung, LG");
            put("rules", Arrays.asList("nullabe", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Display SKU");
            put("rules", Arrays.asList("nullable", "20char", "alphanum"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Refresh rate in Hz");
            put("rules", Arrays.asList("nullable", "3char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Variable refresh range max Hz");
            put("rules", Arrays.asList("nullable", "3char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Variable refresh range max Hz");
            put("rules", Arrays.asList("nullable", "3char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "RAM");
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Ram Brand");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Ram SKU");
            put("rules", Arrays.asList("nullable", "20char", "alphanum"));
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select RAM Channels","Single","Dual"));
        }});
        
        map.put(autoCounter++, new HashMap<String, List>(){{
            put("Select", Arrays.asList("Select RAM Type","DDR3","DDR4","LPDDR4","HBM"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "RAM Capacity in GB");
            put("rules", Arrays.asList("notnull", "5char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "Storage");
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Storage Brand");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Storage SKU");
            put("rules", Arrays.asList("nullable", "20char", "alphanum"));
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
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "Battery");
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Battery Brand");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Battery SKU");
            put("rules", Arrays.asList("nullable", "20char", "alphanum"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Storage Voltage");
            put("rules", Arrays.asList("nullable", "5char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Storage Capacity");
            put("rules", Arrays.asList("notnull", "6char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Battery Cell Count");
            put("rules", Arrays.asList("nullable", "2char", "num"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Battery Chemistry");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("divider", "Accessories");
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "SD card");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "DVD Drive");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Finger Print Scanner");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        map.put(autoCounter++, new HashMap<String, Object>(){{
            put("text", "Windows Hello Camera");
            put("rules", Arrays.asList("nullable", "20char", "alpha"));
        }});
        
        
        return map;
    }
}