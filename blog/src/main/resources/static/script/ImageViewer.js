class ImageViewer {
    constructor(parent) {
        this.id = "IMG" + uuid();
        this.parent = parent;
        //method chaining
        return this;
    }
    setParent(parent) {
        this.parent = parent;
        //method chaining
        return this;
    }
    getParent() {
        return this.parent;
    }
    getId() {
        return this.id;
    }
    setClassList(classList) {
        this.classList = classList;
        //method chainin
        return this;
    }
    getClassList() {
        return this.classList;
    }
    template() {
        return `<!-- image picker -->
        <div class="row m-0 w-100">
          <div
            style="background-color: rgba(69, 79, 94, 0.75); position: fixed; top: 0; height: 100%; left: 0; width: 100%;"
          >
            <div
              class="row p-0 border shadow rounded col-sm-4 mx-auto bg-light"
              style="top:15%; max-height: 600px; overflow-y: scroll;"
            >
              <!-- header -->
              <nav class="navbar navbar-light bg-dark sticky-top col-sm-12">
                <ul class="navbar-nav">
                  <!-- admin controls -->
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link text-white dropdown-toggle"
                      href="#"
                      id="navbarDropdown"
                      role="button"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                      style="font-size: 20px;"
                    >
                      Options
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item" href="#">sort by date</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">sort by name</a>
                    </div>
                  </li>
                </ul>
                <input
                  type="text"
                  class="form-control col-sm-7"
                  placeholder="search"
                />
                <button class="col-sm-2 btn btn-secondary mt-1 mb-1">
                  <i class="material-icons"> close </i>
                </button>
    
                <input
                  type="text"
                  class="form-control col-sm-12"
                  placeholder="ex: http://your/file/here.jpg"
                />
              </nav>
              <!-- /header -->
    
              <div class="col-sm-4 p-1">
                <img
                  src="https://via.placeholder.com/300"
                  class="rounded"
                  width="100%"
                  height="100%"
                />
              </div>
              <div class="col-sm-4 p-1">
                <img
                  src="https://via.placeholder.com/300"
                  class="rounded"
                  width="100%"
                  height="100%"
                />
              </div>
              <div class="col-sm-4 p-1">
                <img
                  src="https://via.placeholder.com/300"
                  class="rounded"
                  width="100%"
                  height="100%"
                />
              </div>
              <div class="col-sm-4 p-1">
                <img
                  src="https://via.placeholder.com/300"
                  class="rounded"
                  width="100%"
                  height="100%"
                />
              </div>
              <div class="col-sm-4 p-1">
                <img
                  src="https://via.placeholder.com/300"
                  class="rounded"
                  width="100%"
                  height="100%"
                />
              </div>
              <div class="col-sm-4 p-1">
                <img
                  src="https://via.placeholder.com/300"
                  class="rounded"
                  width="100%"
                  height="100%"
                />
              </div>
              <div class="col-sm-4 p-1">
                <img
                  src="https://via.placeholder.com/300"
                  class="rounded"
                  width="100%"
                  height="100%"
                />
              </div>
            </div>
          </div>
        </div>
        <!-- image picker -->`;
    }
    print() {
        console.info(this)
        //method chaining
        return this;
    }
}