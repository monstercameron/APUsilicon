class APUManager {
    constructor(parent) {
        this.parent = parent;
        this.id = 'APM' + uuid();
        this.apu = new APU(parent);
        return this;
    }
    getParent() {
        return this.parent;
    }
    getAPU(){
        return this.apu;
    }
    fetchAPU() {

    }
    sendAPU() {
        fetch('http://localhost.:8080/database/apu/add', {
            method: "POST",
            headers: {
              "email": this.getParent().getAdmin().getEmail(),
              "token": localStorage.token
            },
            body: JSON.stringify(this.getAPU().build()) 
          })
          .then(response => response.json())
          .then(data => {
              this.getParent().notify(data.message);
          })
          .catch(error => console.log(error));
    }
    viewForm() {
        this.getParent().setViewOnly(VIEW.ADDAPU).update();
    }
    newForm(){

    }
}