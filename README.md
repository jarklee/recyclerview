# recyclerview
[![](https://jitpack.io/v/jarklee/recyclerview.svg)](https://jitpack.io/#jarklee/recyclerview)

*This project include all stuff support for recyclerview. Currently support expandable recyclerview adapter.*

#Expandable Recyclerview module.

##Install.


```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

compile 'com.github.jarklee.recyclerview:expandable:<recyclerview-version>'
```
##How to use.

* Instance adapter for recyclerview.


```
public class ExpandableAdapter extends RecyclerViewModuleAdapter<ExpandableViewHolder> {

    @Inject
    public ExpandableAdapter() {
        super(null);
    }
}
```
* Instance expandable module for adapter.

```
public class ExpandableModule extends ExpandableRecyclerViewModule<GroupView, ChildView> {

    public ExpandableModule(List<? extends ExpandableGroup> expandableGroups) {
        super(expandableGroups);
    }

    @Override
    public GroupView onCreateGroupViewHolder(ViewGroup viewGroup, int groupViewType) {
        return GroupView.create(viewGroup);
    }

    @Override
    public ChildView onCreateChildViewHolder(ViewGroup viewGroup, int childViewType) {
        return ChildView.create(viewGroup);
    }
}
```

* Attach module to adapter.

```
ExpandableAdapter adapter = new ExpandableAdapter();

ExpandableGroup<String> group1 = new ExpandableGroup<>("group1");
group1.add("item1", false);
group1.add("item2", false);
group1.add("item3", false);

List<String> groupItems2 = new ArrayList<>();
groupItems2.add("item1");
groupItems2.add("item2");
groupItems2.add("item3");
groupItems2.add("item4");
groupItems2.add("item5");
ExpandableGroup<String> group2 = new ExpandableGroup<>("group2", groupItems2);

List<ExpandableGroup<String>> groups = new ArrayList<>();
groups.add(group1);
groups.add(group2);

ExpandableModule mainModule = new ExpandableModule(groups);

adapter.setRecyclerViewModule(module);

recyclerView.setAdapter(adapter);
```
##Customize.

* Override method two methods below to create as many item type as you want.

```
public int getChildViewType(int groupPosition, int childPosition) {
    return 0;
}

public int getGroupViewType(int groupPosition) {
    return 0;
}

```
###See example application.

[https://github.com/jarklee/recyclerview/tree/master/app](example)
