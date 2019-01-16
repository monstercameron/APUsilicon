class NewPost {
  constructor(list = postList) {
    this.id = "id" + uuid();
    this.editorId = "edit" + uuid();
    this.toolbarOptions = [
      ["bold", "italic", "underline", "strike"], // toggled buttons
      ["blockquote", "code-block"],
      [{ header: 1 }, { header: 2 }], // custom button values
      [{ list: "ordered" }, { list: "bullet" }],
      [{ script: "sub" }, { script: "super" }], // superscript/subscript
      [{ indent: "-1" }, { indent: "+1" }], // outdent/indent
      [{ direction: "rtl" }], // text direction
      [{ size: ["small", false, "large", "huge"] }], // custom dropdown
      [{ header: [1, 2, 3, 4, 5, 6, false] }],
      [{ color: [] }, { background: [] }], // dropdown with defaults from theme
      [{ font: [] }],
      [{ align: [] }],
      ["clean"] // remove formatting button
    ];
    this.highlight = hljs.configure({
      // optionally configure hljs
      languages: ["javascript", "ruby", "python"]
    });
    this.editorOptions = {
      modules: {
        syntax: true,
        toolbar: this.toolbarOptions
      },
      theme: "snow"
    };
    this.category = [];
    list.push(this);
  }

  remove() {
    var el = document.querySelector("#" + this.id);
    el.parentNode.removeChild(el);
    this.editor = null;
  }

  setRoot(root) {
    this.root = "#" + root;
  }

  insert() {
    document.querySelector(this.root).innerHTML += this.get();
    this.editor = new Quill("#" + this.editorId, this.editorOptions);
  }

  addCategory(response) {
    response.forEach(cat => {
      this.category.push(cat);
    });
  }

  uploadImage() {
    var el = document.querySelector("#" + this.id);
    var data = el.children[0].children[0].children[1].children[0];
    var file = data.files[0];

    console.log(file);

    var reader = new FileReader();

    if (file) {
      reader.readAsDataURL(file);
      //reader.readAsText(file);
    }
    // fetch("",{
    //     method: "POST",
    //     mode: "no-cors",
    //     headers: {
    //     "Content-Type": "application/json",
    //     "test":"test",
    //     },
    //     body:reader.result
    // })
    //   .then(function(response) {
    //       console.log(response);
    //   });

    return reader;
  }

  buildResponse() {
    var el = document.querySelector("#" + this.id);
    var data = el.children[0].children[0].children;
    var response = {};
    response["imageUrl"] = this.imageSrc;
    response["mediaType"] = data[2].children[0].value;
    response["headline"] = data[3].children[0].value;
    response["author"] = data[4].children[0].value;
    response["tags"] = data[5].children[0].value;
    response["category"] = data[6].children[0].value;
    response["body"] = this.editor.getText(0);

    return response;
  }

  get() {
    return `<div id="${this.id}" class="row m-3">
        <div class="col-sm-8 border mx-auto rounded shadow">
          <div class="container pb-3">
            <div class="row">
              <div class="col-sm-1 p-1 ml-auto">
                <button class="btn btn-danger" onclick="deletePost('${
                  this.id
                }')">
                  <i class="material-icons"> close </i>
                </button>
              </div>
            </div>
            <div class="row mb-1">
              <input type="file" class="btn btn-primary btn-block" />
            </div>
            <div class="row mb-1">
              <select class="form-control" name="" id="">
                <option selected value="image">Image</option>
                <option value="video">Video</option>
              </select>
            </div>
            <div class="row mb-1">
              <input type="text" class="form-control" placeholder="Headline" value"test"/>
            </div>
            <div class="row mb-1">
              <input type="text" class="form-control" placeholder="Author" />
            </div>
            <div class="row mb-1">
              <input type="text" class="form-control" placeholder="Tags" />
            </div>
            <div class="row mb-1">
              <select class="form-control" name="" id="">
                    ${this.category
                      .map(cat => {
                        return `<option value="${cat}">${cat}</option>`;
                      })
                      .join("")}
              </select>
            </div>
            <div class="row mb-2">
              <div id="${
                this.editorId
              }" class="w-100" style="height: 256px;"></div>
            </div>
            <div class="row mb-1">
              <button class="btn btn-primary btn-block" onclick="console.log(newPost.uploadImage())">Send</button>
            </div>
          </div>
        </div>
      </div>`;
  }
}
