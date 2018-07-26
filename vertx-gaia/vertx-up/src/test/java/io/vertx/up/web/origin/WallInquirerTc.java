package io.vertx.up.web.origin;

import io.zero.quiz.ScanBase;
import io.vertx.quiz.example.WallKeeper2;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.zero.exception.WallDuplicatedException;
import io.zero.epic.Ut;
import org.junit.Test;

import java.util.Set;

public class WallInquirerTc extends ScanBase {

    private final Inquirer<Set<Cliff>> walls =
            Ut.singleton(WallInquirer.class);

    @Test(expected = WallDuplicatedException.class)
    public void testScan() {
        final Set<Class<?>> classes = this.getClasses();
        this.walls.scan(classes);
    }

    @Test
    public void testScanCorrect() {
        final Set<Class<?>> classes = this.getClasses();
        classes.remove(WallKeeper2.class);
        final Set<Cliff> treeResult = this.walls.scan(classes);
        for (final Cliff instance : treeResult) {
            System.out.println(instance);
        }
    }
}
