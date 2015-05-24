package controllers;
import dao.ClienteDao;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.test.index;


public class TestController extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result carga() {
        try {
            flash("success", "Carga inicial finalizada com sucesso.");
        } catch (Exception e) {
            flash("error", "Ocorreu um erro na carga inicial : " + e.getMessage());
        }
        return ok(index.render());
    }

    public static Result pool() {
        try {
            flash("success", "Teste de pool de conexões finalizado com sucesso.");
        } catch (Exception e) {
            flash("error", "Ocorreu um erro ao testar pool de conexões: " + e.getMessage());
        }
        return ok(index.render());
    }

    public static Result cache() {
        try {
            flash("success", "Teste de cache finalizado com sucesso.");
        } catch (Exception e) {
            flash("error", "Ocorreu um erro ao testar cache: " + e.getMessage());
        }
        return ok(index.render());
    }

}