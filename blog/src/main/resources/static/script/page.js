class Page {
  constructor(root = "body") {
    this.id = "PAG" + uuid();
    this.root = root;
    this.panelList = [];
    console.info("Building Page    :" + this.id);
  }
  setRoot(root) {
    this.root = root;
  }
  getRoot() {
    return this.root;
  }
  getId() {
    return this.id;
  }
  addNav(item) {
    item.setRoot(this.root);
    this.panelList.unshift(item);
  }
  addPanel(item) {
    item.setRoot(this.id);
    this.panelList.push(item);
  }
  getPanels() {
    return this.panelList;
  }
  setVisible() {
    this.visible = "collapse";
  }
  setInvisible() {
    this.visible = "";
  }
  getVisible() {
    return this.visible;
  }
  insert() {
    document.querySelector(this.root).innerHTML += this.get();
  }
  clearBody() {
    this.html = document.querySelector("body");
    while (this.html.firstChild) {
      this.html.removeChild(this.html.firstChild);
    }
  }
  removePanel(id) {
    console.log("Removing Post    :" + id);
    this.panelList = this.panelList.filter(item => item.id !== id);
    this.update();
  }
  context(id) {
    console.log("Context  For     :" + id);
    var obj = null;
    this.panelList.forEach(item => {
      if (item.getId() == id) {
        obj = item;
        return;
      }
    });
    return obj;
  }
  toggleFullscreen(id) {
    console.log("Toggling Fullscreen For Post:" + id);
    this.panelList.forEach(post => {
      if (post.getId() == id) {
        post.toggleFullscreen();
      }
    });
    this.update();
  }
  update() {
    console.log("Updating Panel   :" + this.id);
    this.clearBody();
    this.insert();
    this.panelList.forEach(post => {
      post.insert();
    });
  }
  newLogin() {
    var login = new Login();
    console.log('page root-->'+this.getId());


    login.setRoot(this.getId());
    console.log('login root append to-->'+login.getRoot());


    this.panelList.splice(1, 0, login);

    console.log(this);
    console.log(login);

    this.update();
  }
  get() {
    return `<div class="container-fluid ${this.visible}" id="${
      this.id
    }" style="scrollbar-width: none;"></div>`;
  }
}
