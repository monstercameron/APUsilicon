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
        return `
        <div style="background-color: rgba(97, 117, 146, 0.288); position: fixed; top: 0; height: 100%; left: 0; width: 100%; z-index:100;">
        <div class="row p-0 col-sm-4 border shadow-lg rounded mx-auto my-auto bg-light" style="position: fixed; left: 0; right: 0; top: 0; bottom: 0;
         min-height: 300px; max-height: 600px;  min-width: 300px; max-width: 600px; overflow-y: scroll;">
          <!-- header -->
          <nav class="navbar navbar-light bg-dark sticky-top col-sm-12">
            <ul class="navbar-nav">
              <!-- admin controls -->
              <li class="nav-item dropdown">
                <a class="nav-link text-white dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="font-size: 20px;">
                  Options
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="#">sort by date</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#">sort by name</a>
                </div>
              </li>
            </ul>
            <input type="text" class="form-control col-sm-7" placeholder="search">
            <button class="col-sm-1 btn btn-outline-light btn-sm mt-1 mb-1">
              <i class="material-icons"> close </i>
            </button>

            <input type="text" class="form-control col-sm-12" placeholder="ex: http://your/file/here.jpg">
          </nav>
          <!-- /header -->

          <!-- image array -->
          <div>
            <div class="col-sm-4 p-1" style="height:300px;">
              <img src="https://via.placeholder.com/300" class="rounded" width="100%" height="100%">
            </div>
            <div class="col-sm-4 p-1" style="height:300px;">
              <img src="https://via.placeholder.com/300" class="rounded" width="100%" height="100%">
            </div>
            <div class="col-sm-4 p-1" style="height:300px;">
              <img src="https://via.placeholder.com/300" class="rounded" width="100%" height="100%">
            </div>
            <div class="col-sm-4 p-1" style="height:300px;">
              <img src="https://via.placeholder.com/300" class="rounded" width="100%" height="100%">
            </div>
          </div>
          <!-- /image array -->

        </div>
      </div>`;
    }
    print() {
        console.info(this)
        //method chaining
        return this;
    }
}