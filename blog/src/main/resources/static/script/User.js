class User {
  constructor(name, token) {
    this.name = name;
    this.token = token;
  }

  setName(name) {
    this.name = name;
  }

  getName() {
    return this.name;
  }

  setToken(token) {
    this.token = token;
  }

  getToken() {
    return this.token;
  }
}
