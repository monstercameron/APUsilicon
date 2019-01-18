class Post {
  constructor(size = POSTSIZE.PREVIEW) {
    this.id = "PST" + uuid();
    this.size = size;
    console.log("Building Post    :" + this.id);
    this.isFullscreen = false;
  }
  setImgsrc(imgsrc) {
    this.imgsrc = imgsrc;
  }
  setAuthor(author) {
    this.author = author;
  }
  setHeadline(headline) {
    this.headline = headline;
  }
  setDate(date) {
    this.date = date;
  }
  setTags(tags) {
    this.tags = tags;
  }
  setCategory(category) {
    this.category = category;
  }
  setBody(body) {
    this.body = body;
  }
  setSize(size) {
    this.size = size;
  }
  setRoot(root) {
    this.root = "#" + root;
  }
  getRoot() {
    return this.root;
  }
  getId() {
    return this.id;
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
  getIsFullscreen() {
    return this.isFullscreen;
  }
  toggleFullscreen() {
    if (this.isFullscreen) {
      this.size = POSTSIZE.FULLSCREEN;
    } else {
      this.size = POSTSIZE.PREVIEW;
    }
    this.isFullscreen = !this.isFullscreen;
  }
  getFullscreen() {
    if (this.isFullscreen != true) {
      return "";
    } else {
      return "collapse";
    }
  }
  getExpandButton() {
    if (this.isFullscreen) {
      return "expand_less";
    } else {
      return "expand_more";
    }
  }
  get() {
    return `
    <div id="post" class="col-sm-${
      this.size
    } mx-auto border mt-5 mb-5 shadow fields">
    <div class="col-sm-12 mt-1 mx-auto">
      <button class="btn btn-danger" onclick="page.toggleFullscreen('${
        this.id
      }')">
        <i class="material-icons"> ${this.getExpandButton()} </i>
      </button>
    </div>
    <div class="col-sm-4 border mt-1 p-0 mx-auto">
      <img
        src="${this.imgsrc}"
        width="100%"
        style="post-image"
      />
    </div>
    <div class="col-sm-12 text-center mx-auto border-bottom" onclick="page.toggleFullscreen('${
      this.id
    }')">${this.headline}</div>
    <div class="col-sm-12 text-center mx-auto border-bottom">${
      this.author
    }</div>
    <div class="col-sm-12 text-center mx-auto border-bottom">${this.date}</div>
    <div class="col-sm-12 text-center mx-auto border-bottom">
    ${this.tags
      .split(",")
      .map(tag => {
        return `<button class="btn btn-outline-secondary m-1">${tag}</button>`;
      })
      .join("")}</div>
      <div class="col-sm-12 text-center mx-auto border-bottom">
      <button class="btn btn-outline-secondary m-1">${this.category}</button>
      </div>
    <div id="${this.id +"-body"}" class="col-sm-12 mx-auto ${this.getFullscreen()}">${
      this.body
    }</div>
  </div>`;
  }
}

const POSTSIZE = {
  PREVIEW: 8,
  FULLSCREEN: 12
};
