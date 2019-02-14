class Nav {
  constructor(parent, brand) {
    this.id = "NAV" + uuid();
    this.parent = parent;
    this.brand = brand;
    this.links = {};
    this.functions = {};
    this.search = '';
    this.searchId = "SCH" + uuid();
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
    return this.parent;
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
    this.links[title] = {
      url: url,
      target: target
    };
    //method chaining
    return this;
  }
  addFunction(title, func) {
    this.functions[title] = func;
    //method chaining
    return this;
  }
  removeLink(title) {
    //method chaining
    return this;
  }
  addAdminpanel() {
    if (this.parent.hasAdmin()) {
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

    } else {
      return '';
    }
  }
  setSearch(search) {
    this.search = search;
    //method chaining
    return this;
  }
  getSearch() {
    return this.search;
  }
  setSearchType(type) {
    this.searchType = type;
    return this;
  }
  getSearchType() {
    return this.searchType;
  }
  filterPage(e) {
    this.search = e.value;
    //console.log(this.search);
    let requests = new RequestMan(this.getParent());
    this.parent.setPageNumber(0);
    requests.fetchBlogs(this.search, 'tag');
    //method chaining
    return this;
  }
  searchFocus() {
    let searchField = document.querySelector('#' + this.searchId);
    //console.error("cursor pos: " + searchField.selectionStart);
    this.setCaretPosition(searchField, searchField.value.length);
    //method chaining;
    return this;
  }
  /**
   * Sets the cursor to the end of the input
   */
  setCaretPosition(elem, caretPos) {
    var range;

    if (elem.createTextRange) {
      range = elem.createTextRange();
      range.move('character', caretPos);
      range.select();
    } else {
      elem.focus();
      if (elem.selectionStart !== undefined) {
        elem.setSelectionRange(caretPos, caretPos);
      }
    }
  }
  template() {
    return `
        <!-- Brand -->
        <a class="navbar-brand" href="#" onclick="page.setView(2).update();">
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
                class="nav-link text-white pointer" 
                onclick="window.open(
                  '${this.links[key]["url"]}',
                  '_${this.links[key]["target"]}'
                );"
              >${key}</li>`;
            })
            .join("")}
            ${this.addAdminpanel()}
          </ul>
            <div class="form-inline ml-auto">
            <input 
              id="${this.searchId}" 
              class="form-control col-sm-auto" 
              type="text" placeholder="Search" 
              value="${this.search}"
              onkeyup="page.getNav().filterPage(this).getParent().update()">
        
              <!-- Button Modal -->
              <button type="button" class="btn btn-outline-light btn-sm m-1" data-toggle="modal" data-target="#loginModal">
                <i class="material-icons">
                  person
                </i>
              </button>
              </div>

              </div>`;
  }
  loginModal() {
    this.email = "EML"+uuid();
    this.credentials = "PWD"+uuid();
    return `
    <!-- The Modal -->
    <div class="modal fade" id="loginModal">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
  
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">Login</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
  
          <!-- Modal body -->
          <div class="modal-body">
            <div class="form-group">
              <label for="email">Email address:</label>
              <input id="${this.email}"  type="email" class="form-control" id="email">
            </div>
            <div class="form-group">
              <label for="pwd">Password:</label>
              <input id="${this.credentials}" type="password" class="form-control" id="pwd">
            </div>
          </div>
  
          <!-- Modal footer -->
          <div class="modal-footer">
            <button class="btn btn-primary" onclick="page.getAdmin().fetchToken()">Login</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
          </div>
  
        </div>
      </div>
    </div>`;
  }
  print() {
    console.info(this)
    //method chaining
    return this;
  }
}