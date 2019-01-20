class Nav {
  constructor(parent, brand) {
    this.id = "NAV" + uuid();
    this.parent = parent;
    this.brand = brand;
    this.links = {};
    this.functions = {};
    console.info("Building Nav       : " + this.id);
    //method chaining
    return this;
  }
  getId() {
    return this.id;
  }
  setParent() {
    this.parent = parent;
    //method chaining
    return this;
  }
  getParent() {
    return this.id;
  }
  setClassList(classes) {
    this.classList = classes;
    //method chaining
    return this;
  }
  getClassList() {
    return this.classList;
  }
  addLink(title, url, target = "self") {
    //console.log(arguments);
    this.links[title] = { url: url, target: target };
    //method chaining
    return this;
  }
  addFunction(title, func){
    this.functions[title] = func; 
    //method chaining
    return this;
  }
  removeLink(title) {
    //method chaining
    return this;
  }
  addAdminpanel(){
    if(this.parent.hasAdmin()){
      let rv = Object.keys(this.functions).map(func => {
          return `
          <a 
            class="dropdown-item" 
            href="#"
            onclick="${this.functions[func]}"
            >
            ${func}
            </a>`;
      }).join('');
      return `<li class="nav-item dropdown">
      <a
        class="nav-link dropdown-toggle text-white"
        href="#"
        id="navbarDropdown"
        role="button"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="false"
      >
        Admin
      </a>
      <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        ${rv}
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Logout</a>
      </div>
    </li>`;

    }else{
      return '';
    }
  }
  template() {
    return `
        <!-- Brand -->
        <a class="navbar-brand" href="#">
        ${this.brand}
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
          ${Object.keys(this.links)
            .map(key => {
              return `
              <li
                class="nav-link text-white" 
                onclick="window.open(
                  '${this.links[key]["url"]}',
                  '_${this.links[key]["target"]}'
                );"
              >${key}</li>`;
            })
            .join("")}
            ${this.addAdminpanel()}
          </ul>
            <input class="form-control col-sm-2 ml-auto" type="text" placeholder="Search">
        </div>`;
  }
  print(){
    console.info(this)
    //method chaining
    return this;
  }
}
