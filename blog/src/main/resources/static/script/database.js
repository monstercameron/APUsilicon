class DB {
  constructor(parent) {
    this.id = "TBL" + uuid();
    this.tableId = "TBL" + uuid();
    this.parent = parent;
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
  initTable() {
    this.table = $("#" + this.tableId).DataTable({
      data: this.data,
      columns: this.columns,
      responsive: true
    });
    //method chaining
    return this;
  }
  getRemoteCols() {
    //fetch latest cols from db
    //method chaining
    return this;
  }
  setCols(list) {
    this.columns = list;
    this.filterList = [];
    for (let index = 0; index < this.columns.length; index++) {
        this.filterList.push({name: this.columns[index].title, id: 'FLTR'+uuid()});
    }
    console.log(this.filterList);
    //method chaining
    return this;
  }
  getCols() {
    return this.columns;
  }
  setDataSet(data) {
    this.data = data;
    //method chaining
    return this;
  }
  filterCols() {
    //console.log(this.columns);        
    for (let index = 0; index < this.columns.length; index++) {
        //travering the list of filters
        this.table.column(index).visible(document.querySelector('#'+this.filterList[index].id).checked);
    }
    //redraw table only
    this.table.columns.adjust().draw( false );
    //method chaining
    return this;
  }
  getDataSet() {
    return data;
  }
  addRow() {
    //to be implemented
  }
  template() {
      console.log(this.columns);
    return `
      <div class="col-sm-11 mx-auto border rounded shadow m-2 p-2">
        <!-- title -->
        <div class="text-center"><h4>APUsilicon Database</h4></div>
        <button
          class="col-sm-2 btn btn-primary text-white mb-1"
        >
          Add New Device
        </button>
        <hr>
        <button
          class="col-sm-2 btn btn-primary text-white mb-2"
          data-toggle="collapse"
          data-target="#database-filter"
        >
          Add/Remove Columns
        </button>
        <div
          id="database-filter"
          class="collapse col-sm-4 mb-3 border round p-2 bg-dark text-white"
        >
        ${this.filterList.map(filter => {
            return `<div><input id="${filter.id}" checked type="checkbox" name="" id="" />${filter.name}</div>`;
          })
          .join("")}
          <button 
            class="btn btn-primary btn-block"
            onclick="page.getDb().filterCols()"
          >
            Apply
          </button>
        </div>
        <table id="${
          this.tableId
        }" class="table table-striped table-bordered" style="width:100%">
        </table>
      </div>`;
  }
  print() {
    console.info(this);
    //method chaining
    return this;
  }
}
