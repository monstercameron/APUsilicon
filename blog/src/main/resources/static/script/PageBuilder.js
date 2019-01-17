
class Pagebuilder {
    constructor() {
        this.id = 'id' + uuid();
        this.root = document.querySelector('body');
        this.postList = [];
    }

    get() {
        return `<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <!-- Brand -->
    <a class="navbar-brand" href="#">Navbar</a>
    <!-- Toggler/collapsibe Button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Navbar links -->
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="#">Link</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Link</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Link</a></li>
      </ul>
    </div>
  </nav>
  <div class="container-fluid" id="${this.id}">
  </div>`;
    }

    insert() {
        this.root.innerHTML = this.get();
    }

    getRoot(){
        return this.id;
    }

    add(item){
        this.postList.push(item);
    }

    getList(){
        return this.postList;
    }

}