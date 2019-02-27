package com.apusilicon.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.apusilicon.blog.classes.imaginery.Category;
import com.apusilicon.blog.classes.imaginery.Owner;
import com.apusilicon.blog.data.AdminDao;
import com.apusilicon.blog.data.CategoryDao;
import com.apusilicon.blog.logic.AuthMan;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "category")
public class CatController {

    @Autowired
    private CategoryDao cats;

    @Autowired
    private AuthMan auth;

    @Autowired
    private AdminDao owners;

    Logger logger = LoggerFactory.getLogger(blogController.class);

    /**
     * View all categories saved to database
     *
     * @return a list of all categories for blog posts
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Category> viewCats() {
        return cats.findAll();
    }

    /**
     * Adds new categories to the database
     *
     * @param request HTTP Headers containing email, token and category
     * @return confirmation/error message as string
     */
    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, String> addCats(
            HttpServletRequest request) {

        // store header info into intermediate variables
        String email = request.getHeader("email");
        String token = request.getHeader("token");
        String category = request.getHeader("category");
        //query database for owner matching email
        Owner owner = owners.findFirstByEmail(email);
        //parsing encrypted token
        Map<String, String> map = new HashMap<>();
        //return error message is parsig token fails
        try {
            map = auth.parseToken(token, owner.getSaltedPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warn("Could not parse incoming token.");
            return new HashMap<String, String>() {
                {
                    put("isSuccess", "False");
                    put("message", "Bad Token/Email");
                }
            };
        }

        if (auth.canWrite(map)) {
            try {
                //checks to see if the category already exists
                for (Category cat : cats.findAll()) {
                    if (cat.getCategory().equals(category)) {
                        return new HashMap<String, String>() {
                            {
                                put("isSuccess", "False");
                                put("message", "Category already Exists");
                            }
                        };
                    }
                }
                //if the category doesnt exist then add to database
                Category theCat = new Category();
                theCat.setCategory(category);
                cats.save(theCat);
                return new HashMap<String, String>() {
                    {
                        put("isSuccess", "True");
                        put("message", category + " saved!");
                    }
                };
            } catch (Exception e) {
                //if the category param is missing return error message
                return new HashMap<String, String>() {
                    {
                        put("isSuccess", "False");
                        put("error", e.toString());
                        put("message", "error saving to database, bad parameter");
                    }
                };
            }
        } else {
            return new HashMap<String, String>() {
                {
                    put("isSuccess", "False");
                    put("message", "No Write Permissions");
                }
            };
        }

    }

    /**
     * Removes a category from the database
     *
     * @param request
     * @return confirmation/error message as string
     */
    @CrossOrigin
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public Map<String, String> removeCats(
            HttpServletRequest request) {
        // store header info in intermediate variable
        String email = request.getHeader("email");
        String token = request.getHeader("token");
        String category = request.getHeader("category");
        //query database for owner matching email
        Owner owner = owners.findFirstByEmail(email);
        //parsing encrypted token
        Map<String, String> map = new HashMap<>();
        //return error message is parsig token fails
        try {
            map = auth.parseToken(token, owner.getSaltedPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warn("Could not parse incoming token.");
            return new HashMap<String, String>() {
                {
                    put("isSuccess", "False");
                    put("message", "Bad Token/Email");
                }
            };
        }

        //check to see if category exists
        if (auth.canWrite(map)) {
            try {
                for (Category cat : cats.findAll()) {
                    if (cat.getCategory().equals(category)) {
                        cats.delete(cat);
                        break;
                    }
                }

                return new HashMap<String, String>() {
                    {
                        put("isSuccess", "True");
                        put("message", category + " Removed!");
                    }
                };
            } catch (Exception e) {
                //if the category param is missing return error message
                return new HashMap<String, String>() {
                    {
                        put("isSuccess", "False");
                        put("error", e.toString());
                        put("message", "error Removing from database, bad parameter");
                    }
                };
            }
        } else {
            return new HashMap<String, String>() {
                {
                    put("isSuccess", "False");
                    put("message", "No Write Permissions");
                }
            };
        }
    }

    /**
     * database form
     * 
     * @return list of text and select fields from database
     */
    @CrossOrigin
    @RequestMapping(value = "databaseform", method = RequestMethod.GET)
    public HashMap<Integer, Object> dataBaseForm() {

        HashMap<Integer, Object> map = new HashMap<>();

        int autoCounter = 1;

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "Brand");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Brand");
                put("field", "Brand");
                put("rules", Arrays.asList("notnull", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "SKU");
                put("field", "sku");
                put("rules", Arrays.asList("notnull", "20char", "alphanum"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Family");
                put("field", "family");
                put("rules", Arrays.asList("notnull", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Weight in Lbs");
                put("field", "weight");
                put("rules", Arrays.asList("nullable", "5char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Price in $ From Manufacturers Website");
                put("field", "price");
                put("rules", Arrays.asList("notnull", "8char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "Links");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Website link");
                put("field", "link");
                put("rules", Arrays.asList("nullable", "anychar", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "APU & Graphics");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("field", "apu");
                put("Select", Arrays.asList("Select an APU", "A", "B", "C"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "External Graphics");
                put("field", "extgraphics");
                put("rules", Arrays.asList("nullable", "20char", "alphanum"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "Display");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Display size in inches");
                put("field", "dispsize");
                put("rules", Arrays.asList("notnull", "4char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Display X Pixels");
                put("field", "dispx");
                put("rules", Arrays.asList("notnull", "4char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Display Y Pixels");
                put("field", "dispy");
                put("rules", Arrays.asList("notnull", "4char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Display Brand ex. AO optronics, Samsung, LG");
                put("field", "dispbrand");
                put("rules", Arrays.asList("nullabe", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Display SKU");
                put("field", "dispsku");
                put("rules", Arrays.asList("nullable", "20char", "alphanum"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Refresh rate in Hz");
                put("field", "disprefresh");
                put("rules", Arrays.asList("nullable", "3char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Variable refresh range max Hz");
                put("field", "disprefreshmin");
                put("rules", Arrays.asList("nullable", "3char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Variable refresh range max Hz");
                put("field", "disprefreshmax");
                put("rules", Arrays.asList("nullable", "3char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "RAM");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Ram Brand");
                put("field", "rambrand");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Ram SKU");
                put("field", "ramsku");
                put("rules", Arrays.asList("nullable", "20char", "alphanum"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("field", "ramchannel");
                put("Select", Arrays.asList("Select RAM Channels", "Single", "Dual"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("field", "ramtech");
                put("Select", Arrays.asList("Select RAM Type", "DDR3", "DDR4", "LPDDR4", "HBM"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "RAM Speed In Mhz");
                put("field", "ramspeed");
                put("rules", Arrays.asList("notnull", "5char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "RAM Capacity in GB");
                put("field", "ramcap");
                put("rules", Arrays.asList("notnull", "5char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "Storage");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Storage Brand");
                put("field", "storbrand");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Storage SKU");
                put("field", "storsku");
                put("rules", Arrays.asList("nullable", "20char", "alphanum"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("field", "storphysical");
                put("Select", Arrays.asList("Select Storage Type",
                        "7MM", "9.5MM", "MSATA", "M22280", "M22260", "M22240"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("field", "stortype");
                put("Select", Arrays.asList("Select Storage Format",
                        "SSD", "SSHD", "HDD"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("field", "storconnector");
                put("Select", Arrays.asList("Select Storage Connector",
                        "M2NVME", "M2SATA", "MSATA", "SATA"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Storage Capacity in GB");
                put("field", "storcap");
                put("rules", Arrays.asList("notnull", "6char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "Battery");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Battery Brand");
                put("field", "batbrand");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Battery SKU");
                put("field", "batsku");
                put("rules", Arrays.asList("nullable", "20char", "alphanum"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Storage Voltage");
                put("field", "batvol");
                put("rules", Arrays.asList("nullable", "5char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Battery Capacity in Wh");
                put("field", "batcap");
                put("rules", Arrays.asList("notnull", "5char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Battery Cell Count");
                put("field", "batcell");
                put("rules", Arrays.asList("nullable", "2char", "num"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Battery Chemistry");
                put("field", "batchem");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("divider", "Accessories");
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "SD card");
                put("field", "sdcard");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "DVD Drive");
                put("field", "dvd");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Bluray Drive");
                put("field", "bluray");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Finger Print Scanner");
                put("field", "finger");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        map.put(autoCounter++, new HashMap<String, Object>() {
            {
                put("text", "Windows Hello Camera");
                put("field", "faceunlock");
                put("rules", Arrays.asList("nullable", "20char", "alpha"));
            }
        });

        return map;
    }
}
