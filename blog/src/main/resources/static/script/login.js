
class Login {
    constructor(list = postList) {
        this.id = "id" + uuid();
        list.push(this);
    }

    setRoot(root) {
        this.root = "#" + root;
    }

    get() {
        return `<div id="${this.id}" class="row m-3">
        <div class="col-sm-8 border rounded shadow mx-auto">
          <div class="row">
            <button class="btn btn-danger ml-auto" onclick="remove('${
            this.id
            }')">
              <i class="material-icons"> close </i>
            </button>
          </div>
          <div class="row m-1 mb-3">
            <div class="col-sm-3 mx-auto text-center">Login</div>
          </div>
          <div class="row m-1 mb-3">
            <input type="text" class="form-control" placeholder="Email" />
          </div>
          <div class="row m-1 mb-3">
            <input
              type="password"
              class="form-control"
              placeholder="Password"
            />
          </div>
          <div class="row m-1 mb-3">
            <button class="btn btn-primary btn-block" onclick="userLogin('${this.id}');">
              login
            </button>
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

    login() {
        var form = document.querySelector("#" + this.id);
        this.email = form.children[0].children[2].children[0];
        this.password = form.children[0].children[3].children[0];

        //this.response = response;
        console.log('Cred pass? --> ' + this.checkErrors(this.email, this.password));
        if (this.checkErrors(this.email, this.password)) {
            console.log('no errors found');
        }
    }

    checkErrors(email, password) {
        var pass = true;
        if (this.email.value == '') {
            email.classList.add('is-invalid');
            pass = false;
            console.log('Email field is empty.');
        }
        if (this.password.value == '') {
            password.classList.add('is-invalid');
            pass = false;
            console.log('Password field is empty.');
        }
        // email regex check
        // password regex check

        return pass;
    }

    logout() {
        //disable token
    }
}