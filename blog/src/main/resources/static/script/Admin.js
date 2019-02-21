class Admin {
    constructor(parent) {
        this.id = "ADM" + uuid();
        this.parent = parent;
        // check local storage for a token
        //console.log(this.checkLocalToken());
        if(this.checkLocalToken()){
            this.token = localStorage.token;
            //console.log(this.token);
        }
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
    getLocalEmail(){
        this.email = localStorage.email;
        return this;
    }
    getEmail(){
        return this.email;
    }
    setEmail(email){
        this.email = email;
        return this;
    }
    saveEmail(email){
        localStorage.setItem("email", email);
        return this;
    }
    deleteTokenAndEmail(){
        console.log('Deleting User Credentials');
        this.token = '';
        localStorage.token = '';
        this.email = '';
        localStorage.email = '';
        return this;
    }
    hasToken(){
        return (this.token != null && this.token !="") ? true : false;
    }
    setToken(token){
        this.token = token;
        return this;
    }
    getToken(){
        return this.token;
    }
    saveToken(token){
        localStorage.setItem("token", token);
        return this;
    }
    getLocalToken(){
        this.token = localStorage.getItem("token");
        return this;
    }
    checkLocalToken(){
        return (localStorage.token != "") ? true : false;
    }
    fetchToken(){
        fetch('http://localhost:8080/auth/login',{
            method:'POST',
            headers:{
                email: document.querySelector('#'+this.getParent().getNav().email).value,
                creds: document.querySelector('#'+this.getParent().getNav().credentials).value
            }
        })
        .then(response => Promise.all([response, response.json()]))
        .then(([response, json]) => {
            if (!response.ok) {
                throw new Error(json.message);
            }
            console.log(json);
            this.setToken(json.token);
            this.saveToken(json.token);
            this.setEmail(json.email)
            this.saveEmail(json.email)
            this.parent.update();
        })
        .catch(exception => {
            var errorMap = new Map([
                [TypeError, "There was a problem fetching the response."],
                [SyntaxError, "There was a problem parsing the response."],
                [Error, exception.message]
            ]).get(exception.constructor);
        });
    }
    print() {
        console.info(this)
        //method chaining
        return this;
    }
}