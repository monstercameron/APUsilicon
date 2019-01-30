class Pagebuilder {
  constructor(root = "body") {
    this.id = "PAG" + uuid();
    this.root = root;
    this.panelList = [];
    this.admin = null;
    this.view = VIEW.DATABASE;
    console.log("Building Page:     : " + this.id);
    //method chaining
    return this;
  }
  //admin
  hasAdmin() {
    // if (this.admin != null) {
    //   return true;
    // } else return false;
    return true;
  }
  //page
  getId() {
    return this.id;
  }
  addTitle(title) {
    console.info("Adding title '" + title + "' to page.");
    //code to add title
    //method chaining
    return this;
  }
  setRoot(root) {
    this.root = root;
    //method chaining
    return this;
  }
  getRoot() {
    return this.root;
  }
  //nav
  addNav(nav) {
    this.nav = nav;

    //method chaining
    return this;
  }
  removeNav(id) {
    if (this.nav.getId() == id) {
      console.info("Removing Nav: " + this.nav.getId());
      this.nav = null;
    }

    //method chaining
    return this;
  }
  getNav() {
    return this.nav;
  }
  //new post panel
  addNewPost(newPost) {
    this.newPost = newPost;
    //method chaining
    return this;
  }
  getNewPost() {
    return this.newPost;
  }
  removeNewPost(id) {
    console.info("Removing New Post   :" + id);
    if (this.newPost.getId() == id) {
      this.newPost = null;
    }
    this.view = VIEW.POSTS;
    //method chaining
    return this;
  }
  createNewPost() {
    this.newPost = new NewPost(this).setClassList("row m-0 w-100");
    this.view = VIEW.NEWPOST;
    //method chaining
    return this;
  }
  editPost(id) {
    console.info("Editing Post        :" + id);
    let data = this.getPanel(id).buildDict();

    this.newPost = new NewPost(this)
      .setClassList("row m-0 w-100")
      .setTitle("Edit")
      .setHead(data["head"])
      .setTags(data["tags"])
      .setCategory(data["category"])
      .setBody(data["body"]);

    this.view = VIEW.NEWPOST;
    //method chaining
    return this;
  }
  viewPanels() {
    this.view = VIEW.POSTS;
    //method chaining
    return this;
  }
  //database
  newDb() {
    if (this.db == null || typeof this.db === "undefined") {
      this.db = new DB(this);
      console.info("DB Instantiated     :" + this.db.getId());
    } else {
      console.error("DB is not null nor undefined");
    }
    //console.log(this.db);
    this.view = VIEW.DATABASE;
    //method chaining
    return this;
  }
  clearDb() {
    this.db = null;
  }
  setDb(db) {
    this.db = db;
    //method chaining
    return this;
  }
  getDb() {
    return this.db;
  }
  veiwDb() {
    this.view = VIEW.DATABASE;
    //method chaining
    return this;
  }
  viewDbEntry() {
    this.dbentry = new DatabaseForm(this);
    this.view = VIEW.DATABASEENTRY;
    //method chaining
    return this;
  }
  getDbEntryForm() {
    return this.dbentry;
  }
  removeDbEntryForm(){
    this.view = VIEW.POSTS;
    return this;
  }
  //model viewer
  newViewer() {
    if (this.viewer == null || typeof this.viewer === "undefined") {
      this.viewer = new Viewer().setParent(this);
    } else {
      console.error("Viewer instance still alive");
    }
    //method chaining
    return this;
  }
  setViewer(modelId) {
    this.viewer.addData(modelId);
    //method chaining
    return this;
  }
  displayViewer() {
    //inject viewer, not for fullscreen refresh
    if (this.viewer == null || typeof this.viewer === "undefined") {
      this.viewer.inject();
    } else {
      console.error("Viewer instance still alive");
    }
    //method chaining
    return this;
  }
  getViewer() {
    //inject viewer, not for fullscreen refresh
    if (this.viewer == null) {
      console.error("Viewer is Null");
    }
    if (typeof this.viewer === "undefined") {
      console.error("Viewer is Undefined");
    }
    //return viewer instance
    return this.viewer;
  }
  destroyViewer() {
    //nulling viewer object
    this.viewer.remove();
    this.viewer = null;
    //method chaining
    return this;
  }
  //panels
  addPanel(panel, position) {
    console.info("Adding Panel to page :" + panel.getId());
    panel;
    switch (position) {
      case 1:
        console.info("Appending Panel");
        this.panelList.push(panel);
        break;
      case 2:
        console.info("Prepending Panel");
        this.panelList.unshift(panel);
        break;
    }
    //method chaining
    return this;
  }
  addPanels(args) {
    if (Array.isArray(args)) {
      //if passing in an array of panels
      for (let index = 0; index < args.length; index++) {
        this.panelList.push(args[index]);
      }
    } else {
      //if passing in multiple panels as arguments
      for (let index = 0; index < arguments.length; index++) {
        this.panelList.push(arguments[index]);
      }
    }

    //method chaining
    return this;
  }
  removePanel(id) {
    console.info("Removing Post    :" + id);
    this.panelList = this.panelList.filter(item => item.id !== id);

    //method chaining
    return this;
  }
  getPanel(id) {
    for (let index = 0; index < this.panelList.length; index++) {
      if (this.panelList[index].getId() == id) {
        console.info("Retreiving panel    :" + id);
        return this.panelList[index];
      }
    }
    console.info("Couldnt Retrieve panel: " + id);
    return null;
  }
  getPanelList() {
    return this.panelList;
  }
  //page display
  clear() {
    this.html = document.querySelector(this.root);
    while (this.html.firstChild) {
      this.html.removeChild(this.html.firstChild);
    }

    //method chaining
    return this;
  }
  draw() {
    console.info("Drawing Page        :" + this.id);
    console.info("With View ID        :" + this.view);

    //clearing display
    this.clear();

    //declaring vars
    let nav, div, classList;

    //appending nav
    nav = document.createElement("nav");
    nav.id = this.nav.getId();
    classList = this.nav.getClassList().split(" ");
    for (let index = 0; index < classList.length; index++) {
      nav.classList.add(classList[index]);
    }
    if (this.nav !== null) {
      nav.innerHTML = this.nav.template();
    } else {
      nav.innerHTML = "Nav Missing. Or default Nav template?";
    }
    document.querySelector(this.root).prepend(nav);

    //drawing view
    switch (this.view) {
      case 1:
        if (this.newPost == null || typeof this.newPost === "undefined") {
          this.view = VIEW.POSTS;
          this.update();
          break;
        }
        //////////////////////////////////////////////////////////
        //                  new post
        //////////////////////////////////////////////////////////
        div = document.createElement("div");
        //adding to class list
        classList = this.newPost.getClassList().split(" ");
        //console.log(classList);
        for (let index = 0; index < classList.length; index++) {
          div.classList.add(classList[index]);
        }
        //panel info
        div.id = this.newPost.getId();
        div.classList.add("row");
        div.innerHTML = this.newPost.template();
        document.querySelector(this.root).append(div);
        //starting the editor after the html is inplace
        this.newPost.initEditor();
        //have to create html before body can be set
        this.newPost.setBodyWait();
        /////////////////////////////////////////////////////////
        break;
      case 2:
        /////////////////////////////////////////////////////////
        //                  panels
        ////////////////////////////////////////////////////////
        for (let index = 0; index < this.panelList.length; index++) {
          div = document.createElement("div");

          //adding to class list
          classList = this.panelList[index].getClassList().split(" ");
          //console.log(classList);
          for (let index = 0; index < classList.length; index++) {
            div.classList.add(classList[index]);
          }

          //panel info
          div.id = this.panelList[index].getId();
          div.classList.add("row");
          div.innerHTML = this.panelList[index].template();
          document.querySelector(this.root).append(div);
        }
        ////////////////////////////////////////////////////////
        break;
      case 3:
        if (this.db == null || typeof this.db === "undefined") {
          this.view = VIEW.POSTS;
          this.update();
          break;
        }
        ////////////////////////////////////////////////////////
        //              database table
        ////////////////////////////////////////////////////////
        div = document.createElement("div");

        //adding to class list
        classList = this.getDb()
          .getClassList()
          .split(" ");
        //console.log(classList);
        for (let index = 0; index < classList.length; index++) {
          div.classList.add(classList[index]);
        }

        //panel info
        div.id = this.getDb().getId();
        div.innerHTML = this.getDb().template();
        document.querySelector(this.root).append(div);
        this.getDb().initTable();
        ////////////////////////////////////////////////////////
        break;
      case 4:
        ////////////////////////////////////////////////////////
        //              database entry
        ////////////////////////////////////////////////////////
        if (typeof this.dbentry === "undefined") {
          this.dbentry = new DatabaseForm();
        }
        div = document.createElement("div");

        //adding to class list
        classList = this.getDb()
          .getClassList()
          .split(" ");
        //console.log(classList);
        for (let index = 0; index < classList.length; index++) {
          div.classList.add(classList[index]);
        }

        //panel info
        div.id = this.dbentry.getId();
        div.innerHTML = this.dbentry.template();
        document.querySelector(this.root).append(div);
        ////////////////////////////////////////////////////////
        break;
        case 5:
        ////////////////////////////////////////////////////////
        //              database entry
        ////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////
        break;
    }

    //method chaining
    return this;
  }
  update() {
    console.info("Updating Page       :" + this.id);
    this.clear();
    this.draw();

    //method chaining
    return this;
  }
  notify(arg) {
    // Let's check if the browser supports notifications
    if (!("Notification" in window)) {
      alert("This browser does not support desktop notification");
    }
    // Let's check whether notification permissions have already been granted
    else if (Notification.permission === "granted") {
      // If it's okay let's create a notification
      var notification = new Notification(arg);
    } // Otherwise, we need to ask the user for permission
    else if (Notification.permission !== "denied") {
      Notification.requestPermission().then(function(permission) {
        // If the user accepts, let's create a notification
        if (permission === "granted") {
          var notification = new Notification(arg);
        }
      });
    }
    return this;
  }
  print() {
    console.info(this);
    //method chaining
    return this;
  }
}

const PANELPOSITION = {
  TOP: 1,
  BOTTOM: 2
};

const VIEW = {
  NEWPOST: 1,
  POSTS: 2,
  DATABASE: 3,
  DATABASEENTRY: 4,
  EDITPOST: 5
};
