#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import re
import shutil
import sys


def edit(src, original, new):
    file = open(src, "r+b")
    content = file.read().decode('utf-8')
    content = re.sub(original, new, content)
    file.seek(0)
    file.truncate()
    file.write(content.encode('utf-8'))
    file.close()


name = sys.argv[1]
package = name.lower()
package = "ademar.study." + package
package_path = name + "/app/src/main/java/" + package.replace(".", "/")

shutil.copytree("Template", name, symlinks=True)


for file_path in [
    # app files
    "/app/src/main/java/ademar/study/template/App.kt",
    "/app/src/main/java/ademar/study/template/ext/TextView.kt",
    "/app/src/main/java/ademar/study/template/ext/View.kt",
    "/app/src/main/java/ademar/study/template/ext/ViewGroup.kt",
    "/app/src/main/java/ademar/study/template/injection/component/ActivityComponent.kt",
    "/app/src/main/java/ademar/study/template/injection/component/FragmentComponent.kt",
    "/app/src/main/java/ademar/study/template/injection/component/ViewHolderComponent.kt",
    "/app/src/main/java/ademar/study/template/injection/module/ActivityModule.kt",
    "/app/src/main/java/ademar/study/template/injection/module/FragmentModule.kt",
    "/app/src/main/java/ademar/study/template/injection/module/ViewHolderModule.kt",
    "/app/src/main/java/ademar/study/template/injection/scope/ActivityScope.kt",
    "/app/src/main/java/ademar/study/template/injection/scope/FragmentScope.kt",
    "/app/src/main/java/ademar/study/template/injection/scope/ViewHolderScope.kt",
    "/app/src/main/java/ademar/study/template/navigation/FlowController.kt",
    "/app/src/main/java/ademar/study/template/navigation/IntentFactory.kt",
    "/app/src/main/java/ademar/study/template/presenter/BasePresenter.kt",
    "/app/src/main/java/ademar/study/template/presenter/LoadDataView.kt",
    "/app/src/main/java/ademar/study/template/presenter/home/HomePresenter.kt",
    "/app/src/main/java/ademar/study/template/presenter/home/HomeView.kt",
    "/app/src/main/java/ademar/study/template/view/base/BaseActivity.kt",
    "/app/src/main/java/ademar/study/template/view/base/BaseFragment.kt",
    "/app/src/main/java/ademar/study/template/view/common/StartActivity.kt",
    "/app/src/main/java/ademar/study/template/view/home/HomeActivity.kt",
    # app test files
    "/app/src/test/java/ademar/study/template/injection/component/ActivityMockComponent.kt",
    "/app/src/test/java/ademar/study/template/injection/component/FragmentMockComponent.kt",
    "/app/src/test/java/ademar/study/template/injection/component/ViewHolderMockComponent.kt",
    "/app/src/test/java/ademar/study/template/injection/module/ActivityMockModule.kt",
    "/app/src/test/java/ademar/study/template/injection/module/FragmentMockModule.kt",
    "/app/src/test/java/ademar/study/template/injection/module/ViewHolderMockModule.kt",
    "/app/src/test/java/ademar/study/template/navigation/FlowControllerTest.kt",
    "/app/src/test/java/ademar/study/template/navigation/IntentFactoryTest.kt",
    "/app/src/test/java/ademar/study/template/presenter/BasePresenterTest.kt",
    "/app/src/test/java/ademar/study/template/presenter/StubLoadDataView.kt",
    "/app/src/test/java/ademar/study/template/presenter/home/HomePresenterTest.kt",
    "/app/src/test/java/ademar/study/template/presenter/home/StubHomeView.kt",
    "/app/src/test/java/ademar/study/template/test/BaseTest.kt",
    "/app/src/test/java/ademar/study/template/test/Fixture.kt",
    "/app/src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker",
    # core files
    "/core/src/main/java/ademar/study/template/core/ext/CharSequence.kt",
    "/core/src/main/java/ademar/study/template/core/ext/Retrofit.kt",
    "/core/src/main/java/ademar/study/template/core/ext/Throwable.kt",
    "/core/src/main/java/ademar/study/template/core/injection/CoreComponent.kt",
    "/core/src/main/java/ademar/study/template/core/injection/CoreModule.kt",
    "/core/src/main/java/ademar/study/template/core/interactor/GetHelloWorldUseCase.kt",
    "/core/src/main/java/ademar/study/template/core/model/Error.kt",
    "/core/src/main/java/ademar/study/template/core/model/HelloWorld.kt",
    "/core/src/main/java/ademar/study/template/core/repository/HelloWorldRepository.kt",
    "/core/src/main/java/ademar/study/template/core/repository/datasource/HelloWorldCloudRepository.kt",
    "/core/src/main/java/ademar/study/template/core/repository/datasource/HelloWorldLocalRepository.kt",
    # core test files
    "/core/src/test/java/ademar/study/template/core/ext/CharSequenceTest.kt",
    "/core/src/test/java/ademar/study/template/core/ext/RetrofitTest.kt",
    "/core/src/test/java/ademar/study/template/core/ext/ThrowableTest.kt",
    "/core/src/test/java/ademar/study/template/core/injection/CoreMockComponent.kt",
    "/core/src/test/java/ademar/study/template/core/injection/CoreMockModule.kt",
    "/core/src/test/java/ademar/study/template/core/interactor/GetHelloWorldUseCaseTest.kt",
    "/core/src/test/java/ademar/study/template/core/model/ErrorTest.kt",
    "/core/src/test/java/ademar/study/template/core/model/HelloWorldTest.kt",
    "/core/src/test/java/ademar/study/template/core/repository/HelloWorldRepositoryTest.kt",
    "/core/src/test/java/ademar/study/template/core/test/BaseTest.kt",
    "/core/src/test/java/ademar/study/template/core/test/Fixture.kt",
    "/core/src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker"
]:
    out_file_path = file_path.replace("template", name.lower())
    os.makedirs(os.path.dirname(name + out_file_path), exist_ok=True)
    shutil.move(name + file_path, name + out_file_path)
    edit(name + out_file_path, "ademar.study.template", package)

edit(name + "/app/src/main/AndroidManifest.xml", "ademar.study.template", package)
edit(name + "/app/src/main/res/values/strings.xml", "Template", name)
edit(name + "/app/src/main/res/values-pt/strings.xml", "Modelo", name)

shutil.rmtree(name + "/.idea")
shutil.rmtree(name + "/.gradle")
os.remove(name + "/Template.iml")
os.remove(name + "/local.properties")
shutil.rmtree(name + "/build")
os.remove(name + "/app/app.iml")
shutil.rmtree(name + "/app/build")
shutil.rmtree(name + "/app/src/main/java/ademar/study/template")
shutil.rmtree(name + "/app/src/test/java/ademar/study/template")
shutil.rmtree(name + "/core/src/main/java/ademar/study/template")
shutil.rmtree(name + "/core/src/test/java/ademar/study/template")
shutil.move(name, "Projects/")
