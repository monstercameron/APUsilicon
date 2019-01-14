APUsilicon Blog and Laptop/portable Database

-back end
--apis
--authorization
---tokens
---password validation

-front end
--ui/ux
--scripts
--api calls


-database
--User Class
---Writer User Class
---Anon Database User Class
--laptop Class

======================================================================
User Class:
-fields
--id (unique int)
--fingerPrint (string)
--name (string)
--email (string)

Writer Class:
-extends User Class:
--password (string)
--recovery password (string)
--role (enum)
---administrator
---contributor

Post Class:
-fields
--id(unique int)
--title(string)
--body(string)
--date(string)
--tags(string)
--category(enum)
--owner(Write class)

Category Class:
-fields
--id(unique int)
--category(enum)

Unique Class:
-fields
--id(unique id)

Hardware Class:
-extends Unique Class
-fields
--Brand(string)
--sku(string)

Display Class:
-extends hardware Class:
-fields
--size (double)
--x (double)
--y (double)
--refresh (int)
--vrr (bool)
--vrrMin (int)
--vrrMax (int)

Ram Class:
-extends hardware Class:
-fields
--type(enum)
---DDR4
---DDR3
--capacity(double)

Storage Class:
-extends hardware Class:
-fields
--type
---7MM
---9.5MM
---mSATA
---M.2 2280
---M.2 2260
---M.2 2240
--format(enum)
---SSD
---SSHD
---HHD
--interface(enum)
---M.2 NVME
---M.2 SATA
---mSATA
---SATA
--capacity(double)

Port Class:
-extends Unique Class
-fields
--Type(enum)
---USB A
---USB C
---HDMI
---Micro HDMI
---Display Port
---3.5mm TRS 
---3.5mm TRRS
---eSATA
---VGA
---RS232
--count(int)

Accessory Class:
-extends Unique Class
-fields
--sdcard(bool)
--dvddrive(bool)
--fingerprint(bool)
--windows hello(bool)

Battery Class:
-extends hardware Class
fields
--voltage(double)
--Capacity(double)
--cell(int)
--chemistry(string)

links Class:
-extends Unique Class
-fields
--title(string)
--url(string)

Laptop Class:
-extends hardware Class:
-fields
--Family(string)
--weight(double)




















