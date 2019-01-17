const POSTSIZE = {
  PREVIEW: 8,
  FULLSCREEN: 12
};

class Post {
  constructor(webResponse, list = postList) {
    this.imgSrc = webResponse["imgSrc"];
    this.heading = webResponse["heading"];
    this.author = webResponse["author"];
    this.tags = webResponse["tags"];
    this.category = webResponse["category"];
    this.body = webResponse["body"];
    this.id = "id" + uuid();
    this.size = POSTSIZE.PREVIEW;
    list.push(this);
  }

  setSize(size) {
    this.size = size;
  }

  setRoot(root) {
    this.root = "#" + root;
  }

  get() {
    return `<div id="${this.id}" class="row m-3">
    <div class="col-sm-${this.size} border p-0 mx-auto bg-dark">
    <div class="col-sm-1 ml-auto p-1 collapse">
    <button class="btn btn-danger" onclick="minimizePost('${this.id}')">
    <i class="material-icons"> minimize </i>
    </button>
    </div>
  <img src="${this.imgSrc}" alt="Logo" style="width:100%;" />
  </div>
  <div class="col-sm-${this.size} border mx-auto">${this.author}</div>
  <div class="col-sm-${this.size} border mx-auto">${this.tags}</div>
  <div class="col-sm-${this.size} border mx-auto">${this.category}</div>
  <div class="col-sm-${this.size} border mx-auto body-preview">
  <div class="" style="height: 25px; overflow: hidden;">${this.body}</div>
  <div class="col-sm-12 text-center"
  style="position:relative; top:-10%;">
  <button class="btn btn-danger" onclick="fullScreenPost('${
    this.id
  }');">Read More</button>
  </div>
  </div>
  </div>`;
  }

  remove() {
    var el = document.querySelector("#" + this.id);
    el.parentNode.removeChild(el);
  }

  hide() {
    var el = document.querySelector("#" + this.id);
    el.classList.add("collapse");
  }

  insert() {
    document.querySelector(this.root).innerHTML += this.get();
  }

  update() {
    this.remove();
    this.insert();
  }

  fullscreen() {
    this.size = POSTSIZE.FULLSCREEN;
    var el = document.querySelector("#" + this.id);
    for (let index = 0; index < el.children.length; index++) {
      //   remove 7 width
      el.children[index].classList.remove("col-sm-7");
      //   add 12 width
      el.children[index].classList.add("col-sm-12");
      //show minimize
      if (index == 0) {
        el.children[index].children[0].classList.remove("collapse");
      }
      //remove gradient and read more button
      if (index == el.children.length - 1) {
        el.children[index].classList.remove("body-preview");
        el.children[index].children[0].setAttribute("style", "height: auto;");
        el.children[index].children[1].classList.add("collapse");
      }
    }
  }

  minimize() {
    this.size = POSTSIZE.PREVIEW;
    var el = document.querySelector("#" + this.id);
    for (let index = 0; index < el.children.length; index++) {
      //   remove 7 width
      el.children[index].classList.remove("col-sm-12");
      //   add 12 width
      el.children[index].classList.add("col-sm-7");
      //show minimize
      if (index == 0) {
        el.children[index].children[0].classList.add("collapse");
      }
      //remove gradient and read more button
      if (index == el.children.length - 1) {
        el.children[index].classList.add("body-preview");
        el.children[index].children[0].setAttribute(
          "style",
          "height: 25px; overflow: hidden;"
        );
        el.children[index].children[1].classList.remove("collapse");
      }
    }
  }
}
