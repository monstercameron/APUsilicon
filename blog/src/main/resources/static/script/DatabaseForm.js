class DatabaseForm {
  constructor(parent) {
    this.id = "DBF" + uuid();
    this.parent = parent;
    this.input = [];
    console.log("Database form       : " + this.id);
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
  fetchInputs(id) {
    let dbFormMan = new DatabaseMan(this.getParent());
    dbFormMan.fetchInputFields(id);
    return '';
  }
  buildDict(){

  }
  print() {
    console.info(this)
    //method chaining
    return this;
  }
  template() {
    return `
      <div class="col-sm-8 mx-auto border rounded shadow m-2 p-2">
        <!-- window action -->
        <div class="col-sm-12 p-0">
          <div class="col-sm-auto ml-auto p-1">
            <button 
            class="btn btn-block bg-light" style="max-height: 24px;"
            onclick="page.removeDbEntryForm().update()">
              <i class="material-icons"> expand_more </i>
            </button>
          </div>
        </div>
        <!-- title -->
        <div class="text-center mt-3 mb-3"><h3>Database Entry Form</h3></div>
        <!-- form -->
        <button class="btn btn-primary btn-block">Submit For Approval</button>
        <!-- image selection -->
        <!-- remote fields -->
        <input
          id="FLD0"
          type="file"
          class="form-control bg-secondary text-white mt-1 mb-1"
          placeholder="Brand"
        />
        ${this.fetchInputs(this.getId())}
        </div>`;
  }
}