class Admin {
    constructor(parent) {
        this.id = "ADM" + uuid();
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
    setToken(token){
        this.token = token;
        return this;
    }
    getToken(){
        return this.token;
    }
    fetchToken(){
        fetch('http://localhost:8080/auth/login',{
            method:'POST',
            headers:{
                email: document.querySelector('#'+this.getParent().getNav().email).value,
                creds: document.querySelector('#'+this.getParent().getNav().credentials).value
            }
        })
        // .then(response => response.json())
        // .then((data) => {
        //     console.log(data);
        // });
        .then(response => Promise.all([response, response.json()]))
        .then(([response, json]) => {
            if (!response.ok) {
                throw new Error(json.message);
            }
            console.log(json);
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