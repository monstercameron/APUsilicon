class NewPost {
  constructor(parent) {
    this.id = "NPT" + uuid();
    this.newCategoryId = "CAT" + uuid();
    this.parent = parent;
    this.category = ['A', 'B'];
    this.postValues = {};
    this.postValues["head"] = "EDT" + uuid();
    this.postValues["image"] = "EDT" + uuid();
    this.postValues["tags"] = "EDT" + uuid();
    this.postValues["category"] = "EDT" + uuid();
    this.title = 'Add New Post';
    //method chaining
    return this;
  }
  setParent(parent) {
    this.parent = parent;
    //method chaining
    return this;
  }
  getparent() {
    return this.parent;
  }
  getId() {
    return this.id;
  }
  getHash(){
    return this.hash;
  }
  setHash(hash){
    this.hash = hash;
    //method chaining
    return this;
  }
  setAction(action){
    this.action = action;
    //method chaining
    return this;
  }
  setClassList(classList) {
    this.classList = classList;
    //method chainin
    return this;
  }
  getClassList() {
    return this.classList;
  }
  initEditor() {
    this.editor = new Quill("#" + this.editorId, {
      theme: "snow"
    });
    //method chaining
    return this;
  }
  getHead() {
    if (typeof this.head === 'undefined')
      return '';
    else
      return this.head;
  }
  getHeadVal() {
    return document.querySelector('#' + this.postValues["head"]).value;
  }
  setHead(head) {
    this.head = head;
    //method chaining
    return this;
  }
  getAuthor() {
    return this.author;
  }
  setAuthor(author) {
    this.author = author;
    //method chaining
    return this;
  }
  getImage() {
    return this.image;
  }
  setImage(image) {
    this.image = image;
    //method chaining
    return this;
  }
  getImageVal(){
    return document.querySelector('#' + this.postValues["image"]).value;
  }
  getTags() {
    if (typeof this.tags === 'undefined')
      return '';
    else
      return this.tags;
  }
  getTagsVal() {
    return document.querySelector('#' + this.postValues["tags"]).value;
  }
  setTags(tags) {
    this.tags = tags;
    //method chaining
    return this;
  }
  getCategory() {
    return this.category;
  }
  getCategoryVal() {
    return document.querySelector('#' + this.postValues["category"]).value;
  }
  setTitle(title) {
    this.title = title;
    return this;
  }
  getTitle() {
    return this.title
  }
  setCategory(category) {
    this.category.push(category);
    //method chaining
    return this;
  }
  getAllCategories() {
    //fetch request to category api?
    //using dummy data returns a list of categories
    return this.category;
  }
  injectNewCategories() {
    //getting reference to select tag
    let select = document.querySelector('#' + this.postValues["category"]);

    //clearing list
    while (select.hasChildNodes()) {
      select.removeChild(select.lastChild);
    }

    //append to select
    select.innerHTML = this.getAllCategories().map(cat => {
      return `<option value="${cat}">${cat}</option>`
    }).join('')

    //method chaining
    return this;
  }
  addNewCategory() {
    //fetch request to add new cats
    let category = document.querySelector('#' + this.newCategoryId);
    console.info('Category added      : ' + category.value);

    //clean up category
    let cat = category.value.trim().split(' ')[0].trim();

    //add category to list if not empty
    if (cat != '')
      this.category.push(cat);

    //console.log(this.category);

    category.value = '';
    //method chaining
    return this;
  }
  getBody() {
    return this.body;
  }
  getBodyHTML() {
    let body = this.editor.root.innerHTML;
    //log(body);
    return body;
  }
  setBody(body) {
    this.body = body;
    //method chaining
    return this;
  }
  setBodyWait() {
    if (typeof this.head === 'undefined')
      this.editor.root.innerHTML = '';
    else
      this.editor.root.innerHTML = this.body;
    //method chaining
    return this;
  }
  buildDict() {
    console.info('building Post         :' + this.id);
    let obj = {
      author: this.author,
      body: this.getBodyHTML().hexEncode(),
      head: this.getHeadVal(),
      tags: this.getTagsVal(),
      category: this.getCategoryVal(),
      image: this.getImageVal(),
      hash: this.hash
    };
    return obj;
  }
  validateNewPost() {
    this.newPost = {};
    let pass = true;

    //these are the only key/value pairs I want to check
    let keys = ['head', 'tags'];
    for (let index = 0; index < keys.length; index++) {
      let field = document.querySelector('#' + this.postValues[keys[index]]);

      //checking if empty
      if (field.value == '') {
        console.error(`${keys[index]} empty for    :${this.id}`);
        this.parent.notify(`${keys[index]} empty for    :${this.id}`);
        field.classList.add('is-invalid');
        pass = false;
      }
      if (field.value != '') {
        //resetting inputs
        field.classList.remove('is-invalid');
      }

      return pass;
    }

    //check editor
    //console.log(this.editor.root.innerHTML);
    if (this.editor.root.innerHTML == '<p><br></p>') {
      console.error(`Editor empty for  :${this.id}`);
      this.parent.notify(`Editor empty for  :${this.id}`);
      this.editor.focus();
    }

    this.newPost = {
      head: this.getHead(),
      tags: this.getTags(),
      category: this.getCategory(),
      body: this.getBody(),
    };
    //method chaining
    return this;
  }
  sendNewPost() {
    if (this.validateNewPost()) {
      requests = new RequestMan();
      requests.sendPost(this.action,this.buildDict());
    }else
      console.error("Can Not Save New Post, There Are Still Errors, Enable Notifications To See Errors.");
    return this;
  }
  template() {
    return `<div
    class="col-sm-8 mx-auto border shadow rounded p-1 mt-1 mb-5"
    style="max-width: 1200px;"
  >
    <!-- window action -->
    <div class="col-sm-12 p-0">
      <div class="col-sm-auto ml-auto p-1">
        <button class="btn btn-block bg-light" style="max-height: 24px;" 
          onclick="page.removeNewPost(page.getNewPost().getId()).update();">
          <i class="material-icons"> expand_more </i>
        </button>
      </div>
    </div>
    <!-- window title -->
    <div class="text-center mt-1 mb-1" style="font-size: 26px;">
      ${this.title}
    </div>
    <!-- heading -->
    <div class="mt-1 mb-1">
      <input
        id="${this.postValues["head"]}"
        type="text"
        class="form-control"
        placeholder="Add Heading"
        value="${this.getHead()}"
      />
    </div>
    <!-- image -->
    <div class="mt-1 mb-1">
      <input
        id="${this.postValues["image"]}"
        type="text"
        class="form-control"
        placeholder="Add Heading"
        value="${this.getImage()}"
      />
    </div>
    <!-- tags -->
    <div class="mt-1 mb-1">
      <input
      id="${
        this.postValues["tags"]
      }" type="text"
       class="form-control" 
       placeholder="Must separate with commas tag, tag, tag" 
       value="${this.getTags()}"/>
    </div>
    <!-- category -->
    <div class="mt-1 mb-1">
      <select
      id="${this.postValues["category"]}" 
      name=""
      class="form-control">
      ${this.getAllCategories().map(cat =>{
          return `<option value="${cat}">${cat}</option>`
        }).join('')
      }
      </select>
    </div>
    <!-- add new category -->
    <div class="row ml-0 mr-1 mt-1 mb-1">
        <input id="${this.newCategoryId}" 
          class="col-sm-10 form-control" 
          type="text" 
          placeholder="Add new category, must not contain spaces"
        />
        <button 
          class="col-sm-2 btn btn-block bg-dark text-white" 
          onclick="page.getNewPost('${this.id}').addNewCategory().injectNewCategories()">
          <i class="material-icons">
            add_box
          </i>
        </button>
    </div>
    <!-- editor -->
    <div class="mt-1 mb-1">
      <div id="${this.editorId}" class="" style="height:300px;"></div>
    </div>
    <!-- submit -->
    <div class="">
      <button 
        class="btn bg-dark text-white btn-block" 
        style="font-size:24px;"
        onclick="page.getNewPost().sendNewPost()"
      >Save</button>
    </div>
  </div>`;
  }
  print() {
    console.info(this)
    //method chaining
    return this;
  }
}