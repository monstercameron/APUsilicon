class interface {
    constructor(root){
        this.id = 'id' + uuid();
        this.root = root;
        //method chaining
       return this;
    }
    getID() {
       return this.id;
    }
    setRoot(){
        this.root = root;
        //method chaining
        return this;
    }
    getRoot(){
        return this.id;
    }
    template(){
        return ``;
    }
}