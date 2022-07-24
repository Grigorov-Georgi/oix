import { render, page } from "./lib.js";
import { homePage } from "./views/home.js";
import { registerPage } from "./views/register.js";


const root = document.querySelector('main')

page(decorateContext)
page('/', homePage)
page('/register', registerPage)

page.start();

function decorateContext(ctx, next){
    ctx.render = (content) => render(content, root);
    // ctx.updateUserNav = updateUserNav;
    next();
}