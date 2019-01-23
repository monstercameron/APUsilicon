class Viewer {
  constructor(parent) {
    this.id = "VWR" + uuid();
    this.parent = parent;
    this.dict = {};
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
  addData(modelId){
    //fetch request mades on the model uuid
    this.dict = modelId
    //method chaining
    return this;
  }
  inject(){
      //finding table div parent
      var tableDivId = this.getParent().getDb().getId();
      //console.log(tableDivId);

      var tableDiv = document.querySelector('#'+tableDivId).parentNode;
      //console.log(tableDiv);

      var viewerInject = document.createElement('div');
      viewerInject.id = this.id;
      viewerInject.innerHTML = this.template();

      //appending template code
      tableDiv.append(viewerInject);
  }
  remove(){
    //finding table div parent
    var tableDivId = this.getParent().getDb().getId();
    //console.log(tableDivId);

    var tableDiv = document.querySelector('#'+tableDivId).parentNode;
    tableDiv.removeChild(tableDiv.lastChild);

  }
  template() {
    return ` <div
    class=""
    style="position: fixed; top:0; left:0; height: 100%; width: 100%; background-color: rgba(140, 150, 179, 0.75); overflow: hidden;"
  >
    <div
      class="border bg-light shadow rounded col-sm-8 mx-auto mb-2"
      style="height:90%; margin-top:60px;">
      <!-- window handler -->
      <div class="row p-1 col-sm-2 mx-auto">
        <button
            class="btn btn-block bg-light"
            onclick="page.destroyViewer();"    
        >
            <i class="material-icons">keyboard_backspace</i>                  
        </button>
      </div>
      <!-- inner window scroll -->
      <div
        class="m-0"  
        style="overflow-y: scroll; overflow-x: hidden; height:90%; margin-top:75px"
      >
      <!-- device image -->
      <div class="row p-1 bg-secondary">
          <div class="mt-2 mb-2 mx-auto">
              <img
              src="https://via.placeholder.com/300"
              class="rounded shadow"
              width="300px"
              height="300px"
            />
          </div>
      </div>
      <!-- brand -->
      <div class="row pt-3">
        <!-- key -->
        <div class="col-sm-2 text-center"><strong>Brand</strong></div>
        <!-- value -->
        <div class="col-sm-10 text-center">HP</div>
      </div>
      <hr>
    </div>
    <!-- end inner window scroll -->
    </div>
  </div>`;
  }
  print() {
    console.info(this);
    //method chaining
    return this;
  }
}
