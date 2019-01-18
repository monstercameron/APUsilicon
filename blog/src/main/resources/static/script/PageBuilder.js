
class Pagebuilder {
    constructor(root = 'body') {
        this.id = 'id' + uuid();
        this.root = root;
        this.panelList = [];
        console.log("Building Page:     : " + this.id);

        //method chaining
        return this;
    }
    //page
    getId() {
        return this.id;
    }
    addTitle(title) {
        console.info('Adding title \'' + title + '\' to page.');
        //code to add title
        //method chaining
        return this;
    }
    //nav
    addNav(nav) {
        this.nav = nav;

        //method chaining
        return this;
    }
    removeNav(id) {
        if (this.nav.getId() == id) {
            console.info('Removing Nav: ' + this.nav.getId());
            this.nav = null;
        }

        //method chaining
        return this;
    }
    getNav() {
        return this.nav;
    }
    //panels
    addPanel(panel, position) {
        console.info('Adding Panel to page :' + panel.getId());
        switch (position) {
            case 1:
                console.info('Appending Panel');
                this.panelList.push(panel);
                break;
            case 2:
                console.info('Prepending Panel');
                this.panelList.unshift(panel);
                break;
        }

        //method chaining
        return this;
    }
    removePanel(id) {
        console.info("Removing Post    :" + id);
        this.panelList = this.panelList.filter(item => item.id !== id);

        //method chaining
        return this;
    }
    getPanel(id) {
        var panel = null;
        this.panelList.foreach(item => {
            if (item.getId() == id) {
                console.info('Retreiving panel: ' + item.getId());
                panel = item;
            }
        });
        return panel;
    }
    getPanelList() {
        return this.panelList;
    }
    //page display
    clear() {
        this.html = document.querySelector(this.root);
        while (this.html.firstChild) {
            this.html.removeChild(this.html.firstChild);
        }

        //method chaining
        return this;
    }
    draw() {
        //add nav
        var nav = document.createElement("nav");
        if (this.nav !== null) {
            nav.innerHTML = this.nav.template();
        } else {
            nav.innerHTML = 'Nav Missing. Or default Nav template?'
        }

        //adding panels
        this.panelList.foreach(panel => {
            var nav = document.createElement("nav");
            nav.id = panel.id;
            nav.classList.add('row');
            nav.innerHTML = panel.template();
        });

        //method chaining
        return this;
    }
    update() {
        this.clear();
        this.draw();

        //method chaining
        return this;
    }
}

let PANELPOSITION = {
    TOP: 1,
    BOTTOM: 2
};