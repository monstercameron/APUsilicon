class NavModal {
  constructor(parent) {
    this.id = "MDL" + uuid();
    this.parent = parent;
    this.inputIds = {};
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
  getInputIds(){
    return this.inputIds;
  }
  template() {
    return `
        <!-- The Modal -->
        <div class="modal fade" id="loginModal">
        <div class="modal-dialog modal-lg">
        <div class="modal-content">
        <!-- Modal Header Login-->
        <div class="modal-header">
        <h4 class="modal-title">Login</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <!-- Modal body Login -->
        <div class="modal-body">
        <div class="form-group">
            <label for="email">Email address:</label>
            <input type="email" class="form-control" id="${this.inputIds["loginEmail"] = "INP"+uuid()}">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="${this.inputIds["loginPassword"] = "INP"+uuid()}">
        </div>
        </div>
        <!-- Modal footer Login-->
        <div class="modal-footer">
        <button class="btn btn-primary btn-block" onclick="page.getAdmin().fetchToken()">Login</button>
        </div>
        <!-- ============================================================================ --> 
        <!-- Modal Header Signup-->
        <div class="modal-header">
        <h4 class="modal-title">Signup</h4>
        </div>
        <!-- Modal body for Signup -->
        <div class="modal-body">
            <div class="form-group">
                <label for="email">Email address:</label>
                <input type="email" class="form-control" id="${this.inputIds["signinEmail"] = "INP"+uuid()}">
            </div>
            <div class="form-group">
                <label for="email">Verify Email address:</label>
                <input type="email" class="form-control" id="${this.inputIds["signinEmailVeri"] = "INP"+uuid()}">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="${this.inputIds["signinPassword"] = "INP"+uuid()}">
            </div>
            <div class="form-group">
                <label for="pwd">Verify Password:</label>
                <input type="password" class="form-control" id="${this.inputIds["signinPasswordVeri"] = "INP"+uuid()}">
            </div>
        </div>
        <!-- Modal footer Signup-->
        <div class="modal-footer">
        <button class="btn btn-primary btn-block" onclick="page.getAdmin().register()">Signup</button>
        </div>
        </div>
        </div>
        </div>`;
  }
  print() {
    console.info(this);
    //method chaining
    return this;
  }
}
