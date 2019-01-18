class Login {
  constructor() {
    this.id = "LGN" + uuid();
    this.form = {};
    this.form["email"] = "FRM" + uuid();
    this.form["password"] = "FRM" + uuid();
    console.info("Building Login   :" + this.id);
  }
  setRoot(root) {
    this.root = root;
  }
  getRoot(){
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
      console.log(this.root);
      console.log(document.querySelector('#'+this.root));
    document.querySelector('#'+this.root).innerHTML += this.get();
  }
  login() {
    this.email = document.querySelector("#" + this.form["email"]);
    this.password = document.querySelector("#" + this.form["password"]);

    if (this.checkErrors(this.email, this.password)) {
      console.info("Login Form       : No errors found");
      //send api request here
      //handle error
      //close panel
      //this.remove()
    }
  }
  checkErrors(email, password) {
    var pass = true;
    if (this.email.value == "") {
      email.classList.add("is-invalid");
      pass = false;
      console.error("Email field is empty.");
    }
    if (this.password.value == "") {
      password.classList.add("is-invalid");
      pass = false;
      console.error("Password field is empty.");
    }
    // email regex check
    // password regex check

    return pass;
  }
  logout() {
    //disable token
  }
  get() {
    return `
      <div id="${this.id}" class="col-sm-8 mx-auto border mt-5 mb-5 shadow">
        <div class="col-sm-12 mt-1 mx-auto">
          <button class="btn btn-danger" onclick="page.removePanel('${
            this.id
          }')">
            <i class="material-icons"> close </i>
          </button>
        </div>
        <div class="col-sm-12 text-center mx-auto">Login</div>
        <div class="col-sm-12 text-center mx-auto">
          <input id="${
            this.form["email"]
          }" type="text" class="form-control" placeholder="Email" />
        </div>
        <div class="col-sm-12 text-center mx-auto mt-3">
          <input id="${
            this.form["password"]
          }" type="password" class="form-control" placeholder="Password" />
        </div>
        <div class="col-sm-12 text-center mx-auto mt-3 mb-3">
          <button class="btn btn-primary btn-block" onclick="page.context('${
            this.id
          }').login();" >Submit</button>
        </div>
      </div>`;
  }
}
