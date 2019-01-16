function uuid() {
  var dt = new Date().getTime();
  var uuid = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function(
    c
  ) {
    var r = (dt + Math.random() * 16) % 16 | 0;
    dt = Math.floor(dt / 16);
    return (c == "x" ? r : (r & 0x3) | 0x8).toString(16);
  });
  return uuid;
}

function fullScreenPost(id) {
  postList.forEach(post => {
    if (post.id == id) {
      post.fullscreen();
    }
  });
}

function minimizePost(id) {
  postList.forEach(post => {
    if (post.id == id) {
      post.minimize();
    }
  });
}

function hidePost(id) {
  postList.forEach(post => {
    if (post.id == id) {
      post.hide();
    }
  });
}

function deletePost(id) {
  postList.forEach(item => {
    if (item.id == id) {
      item.remove();
    }
  });
}

function remove(id) {
  postList.forEach(item => {
    if (item.id == id) {
      item.remove();
    }
  });
}

function test(id) {
    console.log('test');
  postList.forEach(login => {
    if (login.id == id) {
      login.login();
    }
  });
}
