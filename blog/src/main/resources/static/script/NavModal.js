class NavModal {
    constructor(parent) {
        this.id = "MDL" + uuid();
        this.parent = parent;
        this.modalTemplate = 'login';
        console.info("Building Modal      : " + this.id);
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
    setEmailId(id){
        this.email = id;
        return this;
    }
    setPwdId(id){
        this.pwd = id;
        return this;
    }
    setModalTemplate(template) {
        this.modalTemplate = template;
        return this;
    }
    getModalTemplate() {
        return this.modalTemplate;
    }
    isModalTemplate(template) {
        return (template == this.modalTemplate) ? 'Active' : '';
    }
    template(template) {
        //check the template
        //console.log(this.modalTemplate);
        switch (template) {
            case 'signup':
                return `
                <!-- The Modal -->
                <div class="modal fade" id="loginModal">
                <div class="modal-dialog modal-lg">
                <div class="modal-content">
                <!-- Model Menu -->
                <ul class="nav nav-tabs">
                  <li class="nav-item" onclick="page.getNav().getNavModal().setModalTemplate('login').update()">
                    <a class="nav-link ${this.isModalTemplate('login')}" href="#" >Login</a>
                  </li>
                  <li class="nav-item" onclick="page.getNav().getNavModal().setModalTemplate('login').update()">
                    <a class="nav-link ${this.isModalTemplate('signup')}" href="#">Sign Up</a>
                  </li>
                  </li>
                </ul>
                <!-- Modal Header for signup -->
                <div class="modal-header">
                <h4 class="modal-title">Sign Up</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body for Sign -->
                <div class="modal-body">
                <div class="form-group">
                    <label for="email">Email address:</label>
                    <input type="email" class="form-control" id="email">
                </div>
                <div class="form-group">
                    <label for="email">Verify Email address:</label>
                    <input type="email" class="form-control" id="email2">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="pwd">
                </div>
                <div class="form-group">
                    <label for="pwd">Verify Password:</label>
                    <input type="password" class="form-control" id="pwd2">
                </div>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                <button class="btn btn-primary">Login</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
                </div>
                </div>
                </div>`;
            default:
                return `
                <!-- The Modal -->
                <div class="modal fade" id="loginModal">
                <div class="modal-dialog modal-lg">
                <div class="modal-content">
                <!-- Model Menu -->
                <ul class="nav nav-tabs">
                  <li class="nav-item" onclick="page.getNav().getNavModal().setModalTemplate('login').update()">
                    <a class="nav-link ${this.isModalTemplate('login')}" href="#" >Login</a>
                  </li>
                  <li class="nav-item" onclick="page.getNav().getNavModal().setModalTemplate('signup').update()">
                    <a class="nav-link ${this.isModalTemplate('signup')}" href="#">Sign Up</a>
                  </li>
                  </li>
                </ul>
                <!-- Modal Header -->
                <div class="modal-header">
                <h4 class="modal-title">Login</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body for login -->
                <div class="modal-body">
                <div class="form-group">
                    <label for="email">Email address:</label>
                    <input type="email" class="form-control" id="${this.email}">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="${this.pwd}">
                </div>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                <button class="btn btn-primary" onclick="page.getAdmin().fetchToken()">Login</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
                </div>
                </div>
                </div>`;
        }
    }
    update() {
        let elem = document.querySelector('#' + this.getId()).innerHTML = this.template();
    }
    print() {
        console.info(this)
        //method chaining
        return this;
    }
}