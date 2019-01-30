class Post {
  constructor(parent) {
    this.id = "PST" + uuid();
    this.parent = parent;
    //deafult post size refering to preview size
    this.size = 1;
    this.fullScreen = false;
    console.info("Building Post       :" + this.id);
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
  setSize(size) {
    this.size = size;
    //method chaining
    return this;
  }
  getSize() {
    return this.size;
  }
  toggleSize() {
    if (this.fullScreen) {
      this.size = 1;
    } else {
      this.size = 2;
    }
    this.fullScreen = !this.fullScreen;

    //method chaining
    return this;
  }
  setheadLine(headline) {
    this.headLine = headline;
    //method chaining
    return this;
  }
  setAuthor(author) {
    this.author = author;
    //method chaining
    return this;
  }
  setDate(date) {
    this.date = date;
    //method chaining
    return this;
  }
  setTags(tags) {
    this.tags = tags;
    //method chaining
    return this;
  }
  setBody(body) {
    this.body = body;
    //method chaining
    return this;
  }
  setCategory(category) {
    this.category = category;
    //method chaining
    return this;
  }
  setImg(img) {
    this.img = img;
    //method chaining
    return this;
  }
  buildDict(){
    console.info('building Post         :'+this.id);
    let obj = {
      head: this.headLine,
      author: this.author,
      date: this.date,
      tags: this.tags,
      category: this.category,
      body: this.body
    };
    return obj;
  }
  editOption(){
    if(this.parent.hasAdmin()){
      return `
      <div class="col-sm-auto ml-auto p-1">
        <button 
          class="btn btn-block bg-light" 
          style="max-height: 28px;"
          onclick="page.editPost('${this.id}').update()"
        >
          <i class="material-icons"> menu </i>
        </button>
      </div>`;
    }
    return '';
  }
  template() {
    switch (this.size) {
      case 1:
        return `<div
        class="row col-sm-4 mx-auto shadow rounded p-0 mt-1 mb-1"
        style="max-width: 400px;"
        onclick="page.getPanel('${this.id}').toggleSize().getParent().update()"
        >
        <!-- rows -->
        <div
          class="col-sm-12 mx-auto post-headings text-center p-0 border"
          style="min-height: 300px; min-width: 300px;"
        >
          <div
            class="col-sm-auto mx-auto p-0 mt-1"
            style="position: absolute; top: 0; left: 0; height: 100%; width: 100%;"
          >
            <img
              src="${this.img}"
              class="rounded"
              width="100%"
              height="100%"
            />
          </div>
          <!-- headline -->
          <div class="col-sm-12 mb-3 pt-3 border-bottom"
          style="background-color : rgba(255,255,255,.85); font-size: 28px; text-transform: capitalize;">${
            this.headLine
          }</div>
      </div>
    </div>`;
      case 2:
        return `<div
        class="row col-sm-10 mx-auto border shadow rounded p-0 mt-1 mb-1"
        style="max-width: 1200px;"
      >
        <!-- window action -->
        <div class="col-sm-12 p-0">
          ${this.editOption()}
          <div class="col-sm-auto ml-auto p-1">
            <button class="btn btn-block bg-light" style="max-height: 24px;" onclick="page.getPanel('${
              this.id
            }').toggleSize().getParent().update()">
              <i class="material-icons"> expand_more </i>
            </button>
          </div>
        </div>
        <!-- rows -->
        <div
          class="col-sm-12 post-headings text-center"
          style="overflow: hidden;"
        >
          <div
            class="col-sm-auto mx-auto p-0 mt-1"
            style="max-width: 300px; max-height: 300px;"
          >
            <img
              src="${this.img}"
              class="rounded"
              width="100%"
              height="100%"
            />
          </div>
          <!-- headline -->
          <div class="col-sm-12 border-bottom mb-2 pt-3" style="text-transform: capitalize;">${this.headLine}</div>
          <!-- author & date -->
          <div class="col-sm-12 border-bottom mb-2 pt-3" style="text-transform: capitalize;">${this.author} | ${this.date}</div>
          <!-- category and tags -->
          <div class="col-sm-12 border-bottom mb-2 pb-1" style="text-transform: capitalize;">
           <button class="btn btn-outline-secondary ml-1" style="text-transform: capitalize;">${this.category.trim()}</button>
          ${this.tags
            .split(",")
            .map(tag => {
              return `<button class="btn btn-outline-secondary ml-1 mb-1" style="text-transform: capitalize;">${tag.trim()}</button>`;
            })
            .join('')}
          </div>
        <!-- body -->
        <div class="p-2 post-body">${this.body}</div>
      </div>`;
      default:
        return `<h1>Post setSize() can not be null</h1>`;
    }
  }
  print(){
    console.info(this)
    //method chaining
    return this;
  }
}
