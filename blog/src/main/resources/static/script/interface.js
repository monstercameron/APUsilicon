class interface {
  constructor(parent) {
    this.id = "id" + uuid();
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
    return ``;
  }
  print(){
    console.info(this)
    //method chaining
    return this;
  }
}
