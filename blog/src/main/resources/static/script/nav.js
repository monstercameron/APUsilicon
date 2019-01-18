class Nav {
  constructor(root = "body") {
    this.id = "NAV" + uuid();
    this.linkDict = {};
    this.root = root;
    this.type = "nav";
    console.info("Building Nav     :" + this.id);
  }
  getId(){
      return this.id;
  }
  setBrand(brand) {
    this.brand = brand;
  }
  getBrand() {
    return this.brand;
  }
  setRoot(root) {
    this.root = root;
  }
  getRoot() {
    return this.root;
  }
  addLogo(url) {
    this.imgsrc = url;
  }
  getLogo() {
    return this.imgsrc;
  }
  setLogo() {
    if (this.imgsrc == null || this.imgsrc == "") {
      return this.brand;
    } else {
      return `<img src="${this.imgsrc}" alt="Logo" style="width:40px;">`;
    }
  }
  addLink(title, url, func) {
    this.linkDict[title] = url;
    if (arguments[2] != null) {
      this.onclick = func + "()";
    }
  }
  getAllLinks() {
    return Object.values(this.linkDict);
  }
  insert() {
    var nav = document.createElement("obj");
    nav.innerHTML = this.get();
    document.querySelector(this.root).prepend(nav);
  }
  get() {
    return `
        <nav id="${
          this.id
        }" class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
        <!-- Brand -->
        <a class="navbar-brand" href="#">
        ${this.setLogo()}
        </a>
  
        <!-- Toggler/collapsibe Button -->
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#collapsibleNavbar"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
  
        <!-- Navbar links -->
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
          <ul class="navbar-nav text-center">
          ${Object.keys(this.linkDict)
            .map(link => {
              return ` <li class="nav-item"><a class="nav-link" href="${
                this.linkDict[link]
              }" onclick="${this.onclick}">
              ${link}
              </a></li>`;
            })
            .join("")}
          </ul>
            <input class="form-control col-sm-2 ml-auto" type="text" placeholder="Search">
        </div>
      </nav>`;
  }
  update() {
    var el = document.querySelector("#" + this.id);
    var parent = el.parentNode;
    parent.removeChild(el);
    parent.prepend(this.get());
  }
}
