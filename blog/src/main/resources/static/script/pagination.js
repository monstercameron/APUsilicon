class Pagination {
    constructor(parent) {
      this.id = "PGN" + uuid();
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
    template() {
      return `
      <div class="row m-0 mt-5 w-100">
        <div class="mx-auto col-sm-auto p-0 m-0">
          <nav aria-label="Page navigation  mx-auto">
            <ul class="pagination">

            ${this.parent.getPageNumber() == 0 ? '' : `<li class="page-item">
            <a class="page-link" href="#"
                onclick="page.setPageNumber(${this.getParent().getPageNumber()-1}).fetchPanels()">
                Prev
            </a>
            </li>`}
              ${Array(this.parent.getPageCount())
                .fill()
                .map((page, index) => {
                  return `<li class="page-item ${this.getParent().getPageNumber() == index ? 'active' : ''}">
                  <a 
                    class="page-link" 
                    href="#"
                    onclick="page.setPageNumber('${index}').fetchPanels()">
                    ${index+1}
                    </a>
                  </li>`;
                })
                .join('')}
                ${this.parent.getPageNumber() == this.parent.getPageCount()-1 ? '' : `<li class="page-item">
                <a class="page-link" href="#"
                    onclick="page.setPageNumber(${this.getParent().getPageNumber()+1}).fetchPanels()">
                    Next
                </a>
            </li>`}
                
            </ul>
          </nav>
        </div>
      </div>`;
    }
    print(){
      console.info(this)
      //method chaining
      return this;
    }
  }
  